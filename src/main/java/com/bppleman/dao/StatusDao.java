package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface StatusDao {
    List<Status> getStatus(IDParam idParam);
    List<Status> getStatusByIDs(List<Integer> ids);
    Status getTheLastInsertStatus();
    int insertStatus(Status status);
    int updateStatus(Status status);
}
