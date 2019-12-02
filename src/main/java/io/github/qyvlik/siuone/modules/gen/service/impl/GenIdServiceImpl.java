package io.github.qyvlik.siuone.modules.gen.service.impl;

import com.google.common.collect.Lists;
import io.github.qyvlik.siuone.common.wapper.Collections3;
import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.gen.dao.GenIdMapping;
import io.github.qyvlik.siuone.modules.gen.entity.GenId;
import io.github.qyvlik.siuone.modules.gen.service.GenIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 位数	个数	            区间
 * 1位	62	            0 - 61
 * 2位	3844	        62 - 3843
 * 3位	约 23万	        3844 - 238327
 * 4位	约 1400万	    238328 - 14776335
 * 5位	约 9.1亿	        14776336 - 916132831
 * 6位	约 568亿	        916132832 - 56800235583
 */
@Service("genIdService")
public class GenIdServiceImpl extends CrudService<GenId, GenIdMapping> implements GenIdService {

    @Transactional(readOnly = false)
    @Override
    public List<GenId> createPendingList(Integer segmentSize, Integer count) {

        if (segmentSize == null || segmentSize <= 0) {
            throw new RuntimeException("createPendingList failure : segmentSize must bigger than zero");
        }

        if (count == null || count <= 0) {
            throw new RuntimeException("createPendingList failure : count must bigger than zero");
        }

        Long segmentBegin = 238328L;
        List<GenId> latest = this.findList(new GenId(), "segment_begin DESC", 1);

        if (Collections3.isNotEmpty(latest)) {
            segmentBegin = latest.get(0).getSegmentBegin() + segmentSize;
        }

        long currentTimeMillis = System.currentTimeMillis();

        List<GenId> genIds = Lists.newLinkedList();

        while (count-- > 0) {
            GenId genId = new GenId();
            genId.setSegmentBegin(segmentBegin);
            genId.setSegmentSize(segmentSize);
            genId.setMachineId(-1L);
            genId.setState(GenId.STATE_PENDING);
            genId.setCreateTime(currentTimeMillis);
            genId.setUpdateTime(currentTimeMillis);
            genId.setVersion(0);

            this.insert(genId);

            segmentBegin += segmentSize;

            genIds.add(genId);
        }

        return genIds;
    }

    @Override
    public List<GenId> getPendingList(Integer count) {
        GenId query = new GenId();
        query.setState(GenId.STATE_PENDING);
        return this.findList(query, "create_time ASC", count);
    }

    @Transactional(readOnly = false)
    @Override
    public List<GenId> alloc(Long machineId, Integer maxCount) {
        List<GenId> usingList = Lists.newLinkedList();
        List<GenId> pendingList = getPendingList(maxCount);
        for (GenId pendingGenId : pendingList) {
            GenId updater = new GenId();
            updater.setId(pendingGenId.getId());
            updater.setMachineId(machineId);
            updater.setState(GenId.STATE_USING);
            updater.setUpdateTime(System.currentTimeMillis());
            updater.setVersion(pendingGenId.getVersion());
            long effectCount = this.update(updater);
            if (effectCount == 1) {
                pendingGenId = get(pendingGenId.getId());
                usingList.add(pendingGenId);
            }
        }
        return usingList;
    }

    @Transactional(readOnly = false)
    @Override
    public GenId randomAlloc(Long machineId) {
        List<GenId> pendingList = Lists.newArrayList(getPendingList(128));

        if (Collections3.isEmpty(pendingList)) {
            return null;
        }

        int size = pendingList.size();

        GenId genId = pendingList.get((int) (Math.random() * size));
        GenId updater = new GenId();
        updater.setId(genId.getId());
        updater.setMachineId(machineId);
        updater.setState(GenId.STATE_USING);
        updater.setUpdateTime(System.currentTimeMillis());
        updater.setVersion(genId.getVersion());
        long effectCount = this.update(updater);
        if (effectCount == 1) {
            return get(genId.getId());
        }
        return null;
    }
}
