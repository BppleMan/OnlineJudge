package com.bppleman.service;

import com.bppleman.entity.ProblemData;
import org.springframework.stereotype.Repository;

/**
 * Created by BppleMan on 2017/11/21.
 */
@Repository
public interface ProblemDataService {
    ProblemData getProblemDataByProblemId(Integer problemId);
    ProblemData getProblemDataByProblemIdAndShellName(Integer problemId, String shellName);
    boolean insertProblemData(ProblemData problemData);
    boolean updateProblemData(ProblemData problemData);
}
