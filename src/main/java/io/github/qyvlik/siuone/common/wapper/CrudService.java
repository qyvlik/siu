package io.github.qyvlik.siuone.common.wapper;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(readOnly = true)
public abstract class CrudService<E extends DataEntity, M extends CrudMapping<E>> extends BaseService {
    @Autowired
    protected M mapping;


    public E getBySharedIdAndId(Serializable sharedId, Serializable id) {
        return mapping.getBySharedIdAndId(sharedId, id);
    }

    public E get(Serializable id) {
        return mapping.get(id);
    }

    public List<E> findList(E entity) {
        return mapping.findList(entity);
    }

    public Long count(E entity) {
        return mapping.count(entity);
    }

    @Transactional(readOnly = false)
    public Long insert(E entity) {
        return mapping.insert(entity);
    }

    @Transactional(readOnly = false)
    public Long update(E entity) {
        return mapping.update(entity);
    }

    @Transactional(readOnly = false)
    public Long delete(Serializable id) {
        return mapping.delete(id);
    }

    @Transactional(readOnly = false)
    public Long insertList(List<E> list, int batchSize) {
        if (list == null || list.size() <= 0) {
            throw new RuntimeException("insertList list is empty");
        }
        long effectCount = 0;
        if (list.size() > batchSize) {
            List<List<E>> splitList = Collections3.split(list, batchSize);
            for (List<E> subList : splitList) {
                effectCount += mapping.insertList(subList);
            }
        } else {
            effectCount = mapping.insertList(list);
        }

        return effectCount;
    }

    public List<E> findList(E entity, String orderBy, Integer limit) {
        if (limit != null && limit > 0) {
            PageHelper.offsetPage(0, limit, false);
        }

        if (StringUtils.isNotBlank(orderBy)) {
            PageHelper.orderBy(SqlFixInjectionUtils.fixOrderBy(orderBy));
        }

        return Lists.newArrayList(mapping.findList(entity));
    }
}
