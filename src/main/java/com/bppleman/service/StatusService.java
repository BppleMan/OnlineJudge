package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface StatusService {
    List<Status> getStatus(IDParam param);
    List<Status> getStatusByIDs(List<Integer> ids);
    Status getTheLastInsertStatus();
    Integer getProblemXXTimesByUserId(IDParam param, String statusValue);
    boolean insertStatus(Status status);
    boolean updateStatus(Status status);
}
