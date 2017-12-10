package com.bppleman.dao;

import com.bppleman.entity.Data;
import org.springframework.stereotype.Repository;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Repository
public interface DataDao {
    Data getDataByProblemIDAndShellName(int problemId, String shellName);
}
