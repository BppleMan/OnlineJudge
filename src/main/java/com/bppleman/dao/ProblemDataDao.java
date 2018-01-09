package com.bppleman.dao;

import com.bppleman.entity.ProblemData;
import org.springframework.stereotype.Repository;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Repository
public interface ProblemDataDao {
    ProblemData getProblemDataByProblemId(Integer problemId);
    ProblemData getProblemDataByProblemIdAndShellName(Integer problemId, String shellName);
    Integer insertProblemData(ProblemData problemData);
    Integer updateProblemData(ProblemData problemData);
}
