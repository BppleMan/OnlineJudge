package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemRatioService {
    /**
     * 当实体对象是ProblemRatioServiceImpl时参数应当为null
     * 当实体对象时ContestProblemRatioServiceImpl时参数应当包括contestId
     * @param param
     * @return
     */
    Map<Integer, ProblemRatio> getProblemRatioMap(IDParam param);

    /**
     * 当实体对象是ProblemRatioServiceImpl时参数应当包括problemId
     * 当实体对象时ContestProblemRatioServiceImpl时参数应当包括contestId, problemId
     * @param param
     * @return
     */
    ProblemRatio getProblemRatioByProblemId(IDParam param);
    boolean insertProblemRatio(ProblemRatio problemRatio);
    boolean updateProblemRatio(ProblemRatio problemRatio);
    /**
     * 当实体对象是ProblemRatioServiceImpl时参数应当包括problemId
     * 当实体对象时ContestProblemRatioServiceImpl时参数应当包括contestId, problemId
     * @param param
     * @return
     */
    boolean deleteProblemRatio(IDParam param);
}
