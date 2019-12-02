package io.github.qyvlik.siuone.modules.url.service;

import io.github.qyvlik.siuone.modules.url.entity.UrlBlock;

import java.util.List;

public interface UrlBlockService {

    Long delete(Long id);

    Long insert(UrlBlock urlBlock);

    List<UrlBlock> findAllList();

    List<UrlBlock> findList(UrlBlock query);

    Long count(UrlBlock query);
}
