package io.github.qyvlik.siuone.modules.gen.service;

import io.github.qyvlik.siuone.modules.gen.entity.GenId;

import java.util.List;

public interface GenIdService {
    List<GenId> createPendingList(Integer segmentSize, Integer count);

    List<GenId> getPendingList(Integer count);

    List<GenId> alloc(Long machineId, Integer maxCount);

    GenId randomAlloc(Long machineId);
}
