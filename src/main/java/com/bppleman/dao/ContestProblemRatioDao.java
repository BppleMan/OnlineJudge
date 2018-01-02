package com.bppleman.dao;

import com.bppleman.entity.ProblemRatio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestProblemRatioDao {
    List<ProblemRatio> getContestProblemRatios(Integer contestId);
    ProblemRatio getContestProblemRatioByProblemId(Integer contestId, Integer problemId);
    Integer insertContestProblemRatio(ProblemRatio problemRatio);
    Integer updateContestProblemRatio(ProblemRatio problemRatio);
    Integer deleteContestProblemRatio(Integer contestId, Integer problemId);
}
