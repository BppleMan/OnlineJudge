package com.bppleman.service.impl;

import com.bppleman.dao.ProblemLabelDao;
import com.bppleman.entity.Label;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemLabel;
import com.bppleman.service.LabelService;
import com.bppleman.service.ProblemLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("problemLabelService")
public class ProblemLabelServiceImpl implements ProblemLabelService {

    @Resource
    private ProblemLabelDao problemLabelDao;

    @Resource
    private LabelService labelService;

    @Override
    public List<ProblemLabel> getProblemLabelByProblemId(Integer problemId) {
        return problemLabelDao.getProblemLabelByProblemId(problemId);
    }

    @Override
    public List<Problem> getProblemsByLabelValueWithPage(String labelValue, Integer page, Integer length) {
        Integer offset = (page - 1) * length;
        return problemLabelDao.getProblemsByLabelValueWithPage(labelValue, offset, length);
    }

    @Override
    public Integer getProblemCountByLabelValue(String labelValue) {
        return problemLabelDao.getProblemCountByLabelValue(labelValue);
    }

    @Override
    public Map<String, List<Problem>> getLabelProblemsMapByLabelValueWithPage(
            Map<String, Integer> labelCurrentPage, Integer length) {

        List<Label> labels = labelService.getLabels();
        Map<String, List<Problem>> labelToProblemsMap = new HashMap<>();
        for (Label label : labels) {
            for (String labelValue  : label.getValues()) {
                List<Problem> problems = null;
//                这里即是针对不同label的不同分页进行了处理
//                如果此时的label等于将要翻页的label，那么它的新页码应该是labelNewPage
//                否则别的value的翻页页码应该是
                problems = getProblemsByLabelValueWithPage(labelValue, labelCurrentPage.get(labelValue), length);
                labelToProblemsMap.put(labelValue, problems);
            }
        }
        return labelToProblemsMap;
    }

    @Override
    public Map<String, Integer> getLabelPageCountsMap(Set<String> labelValues, Integer countPerPage) {
        Map<String, Integer> labelPageCountMap = new HashMap<>();
        for (String labelValue : labelValues) {
            Integer tableCount = getProblemCountByLabelValue(labelValue);
            Integer pageCount = (int) Math.ceil((double) tableCount / (double) countPerPage);
            labelPageCountMap.put(labelValue, pageCount);
        }
        return labelPageCountMap;
    }

    @Override
    public boolean insertProblemLabel(ProblemLabel problemLabel) {
        boolean result = true;
        if (problemLabelDao.insertProblemLabel(problemLabel) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean updateProblemLabel(ProblemLabel problemLabel) {
        boolean result = true;
        if (problemLabelDao.updateProblemLabel(problemLabel) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean deleteProblemLabelByProblemId(Integer problemId) {
        boolean result = true;
        if (problemLabelDao.deleteProblemLabelByProblemId(problemId) == 0)
            result = false;
        return result;
    }

    @Override
    public boolean deleteProblemLabelByProblemLabelId(Integer problemLabelId) {
        boolean result = true;
        if (problemLabelDao.deleteProblemLabelByProblemLabelId(problemLabelId) != 1)
            result = false;
        return result;
    }
}
