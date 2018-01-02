package com.bppleman.service;

import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemLabel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProblemLabelService {
    List<ProblemLabel> getProblemLabelByProblemId(Integer problemId);
    List<Problem> getProblemsByLabelValueWithPage(String labelValue, Integer page, Integer length);
    Integer getProblemCountByLabelValue(String labelValue);
    Map<String, List<Problem>> getLabelProblemsMapByLabelValueWithPage(Map<String, Integer> labelCurrentPage, Integer length);
    Map<String, Integer> getLabelPageCountsMap(Set<String> labelValues, Integer countPerPage);
    boolean insertProblemLabel(ProblemLabel problemLabel);
    boolean updateProblemLabel(ProblemLabel problemLabel);
    boolean deleteProblemLabelByProblemId(Integer problemId);
    boolean deleteProblemLabelByProblemLabelId(Integer problemLabelId);
}
