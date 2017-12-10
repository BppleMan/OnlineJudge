package com.bppleman.service;

import com.bppleman.entity.IDParam;
import com.bppleman.entity.ProblemRatio;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemRatioService {
    List<ProblemRatio> getProblemRatio(IDParam idParam);
    boolean insertProblemRatio(ProblemRatio problemRatio);
    boolean updateProblemRatio(ProblemRatio problemRatio);
    boolean deleteProblemRatio(List<IDParam> idParams);
    Map<Integer, ProblemRatio> getProblemRatioMap(IDParam idParam);
}
