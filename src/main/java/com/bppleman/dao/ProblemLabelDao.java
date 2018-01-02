package com.bppleman.dao;

import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemLabel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProblemLabelDao {
    List<ProblemLabel> getProblemLabelByProblemId(Integer problemId);
    List<Problem> getProblemsByLabelValueWithPage(String labelValue, Integer offset, Integer length);
    Integer getProblemCountByLabelValue(String labelValue);
    Integer insertProblemLabel(ProblemLabel problemLabel);
    Integer updateProblemLabel(ProblemLabel problemLabel);
    Integer deleteProblemLabelByProblemId(Integer problemId);
    Integer deleteProblemLabelByProblemLabelId(Integer problemLabelId);
}
