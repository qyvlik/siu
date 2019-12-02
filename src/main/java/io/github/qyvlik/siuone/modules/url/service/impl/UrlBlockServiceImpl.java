package io.github.qyvlik.siuone.modules.url.service.impl;

import io.github.qyvlik.siuone.common.wapper.CrudService;
import io.github.qyvlik.siuone.modules.url.dao.UrlBlockMapping;
import io.github.qyvlik.siuone.modules.url.entity.UrlBlock;
import io.github.qyvlik.siuone.modules.url.service.UrlBlockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("urlBlockService")
public class UrlBlockServiceImpl extends CrudService<UrlBlock, UrlBlockMapping> implements UrlBlockService {
    @Transactional(readOnly = false)
    @Override
    public Long delete(Long id) {
        return super.delete(id);
    }

    @Override
    public List<UrlBlock> findAllList() {
        UrlBlock query = new UrlBlock();
        return this.findList(query);
    }
}
