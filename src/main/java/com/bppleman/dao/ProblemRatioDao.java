package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRatioDao {
    List<ProblemRatio> getProblemRatio();
    ProblemRatio getProblemRatioByProblemId(Integer problemId);
    Integer insertProblemRatio(ProblemRatio problemRatio);
    Integer updateProblemRatio(ProblemRatio problemRatio);
    Integer deleteProblemRatio(Integer problemId);
}
