package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface StatusService {
    List<Status> getStatus(IDParam idParam);
    List<Status> getStatusByIDs(List<Integer> ids);
    Status getTheLastInsertStatus();
    boolean insertStatus(Status status);
    boolean updateStatus(Status status);
}
