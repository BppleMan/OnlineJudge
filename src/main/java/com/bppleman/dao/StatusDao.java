package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.Status;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/20.
 */
@Repository
public interface StatusDao {
    List<Status> getStatus(IDParam param);
    List<Status> getStatusByIDs(List<Integer> ids);
    Status getTheLastInsertStatus();
    /**
     * XX代表statusValue
     * 1、AC
     * 2、WA
     * 3、Compile Error
     * ……后续会添加
     * @param param
     * @param statusValue
     * @return
     */
    Integer getProblemXXTimesByUserId(@Param("param") IDParam param, @Param("statueValue") String statusValue);
    Integer insertStatus(Status status);
    Integer updateStatus(Status status);
}
