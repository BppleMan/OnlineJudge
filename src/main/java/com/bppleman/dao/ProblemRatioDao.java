package com.bppleman.dao;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRatioDao {
    List<ProblemRatio> getProblemRatio(IDParam idParam);
    int insertProblemRatio(ProblemRatio problemRatio);
    int updateProblemRatio(ProblemRatio problemRatio);
    int deleteProblemRatio(List<IDParam> idParams);
}
