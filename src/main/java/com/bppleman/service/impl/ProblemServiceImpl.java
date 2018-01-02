package com.bppleman.service.impl;

import com.bppleman.controller.ProblemController;
import com.bppleman.dao.ProblemDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemLabel;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.service.CountService;
import com.bppleman.service.ProblemLabelService;
import com.bppleman.service.ProblemRatioService;
import com.bppleman.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/8.
 */
@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemDao problemDao;

    @Resource
    private ProblemLabelService problemLabelService;

    @Resource
    private ProblemRatioService problemRatioService;

    @Resource
    private CountService countService;

    @Override
    public List<Problem> getAllProblems() {
        return problemDao.getAllProblems();
    }

    @Override
    public List<Problem> getProblemsWithPage(String type, String keyWord, Integer page, Integer length) {
        Integer offset = (page - 1) * length;
        if ((type == null || "".equals(type.trim()))&& (keyWord == null || "".equals(type.trim()))) {
            return problemDao.getProblemsWithPage(offset, length);
        } else if (type.equals(ProblemController.Type.ID)) {
            Integer id = Integer.parseInt(keyWord);
            List<Problem> problems = new ArrayList<>();
            problems.add(getProblemByProblemId(id));
            return problems;
        }
        else if (type.equals(ProblemController.Type.AUTHOR)) {
            return getProblemsByAuthorWithPage(keyWord, offset, length);
        } else if (type.equals(ProblemController.Type.TITLE)){
            return getProblemsByTitleWithPage(keyWord, offset, length);
        } else {
            return getProblemsByLabelWithPage(keyWord, offset, length);
        }
    }

    @Override
    public Problem getProblemByProblemId(Integer id) {
        return problemDao.getProblemByProblemId(id);
    }

    private List<Problem> getProblemsByAuthorWithPage(String author, Integer offset, Integer length) {
        return problemDao.getProblemsByAuthorWithPage(author, offset, length);
    }

    private List<Problem> getProblemsByTitleWithPage(String title, Integer offset, Integer length) {
        return problemDao.getProblemsByTitleWithPage(title, offset, length);
    }

    private List<Problem> getProblemsByLabelWithPage(String label, Integer offset, Integer length) {
        return problemLabelService.getProblemsByLabelValueWithPage(label, offset, length);
    }

    @Override
    public Integer getCount(String type, String keyWord) {
        if ((type == null || "".equals(type.trim()))&& (keyWord == null || "".equals(type.trim()))) {
            return countService.getCount(null, "problem", null, null);
        } else if (type.equals(ProblemController.Type.ID)) {
            return 1;
        } else if (type.equals(ProblemController.Type.AUTHOR)) {
            return countService.getCount(CountService.EQUAL, "problem", type, keyWord);
        } else if (type.equals(ProblemController.Type.TITLE)){
            return countService.getCount(CountService.LIKE, "problem", type, keyWord);
        } else if (type.equals(ProblemController.Type.LABEL)){
            return problemLabelService.getProblemCountByLabelValue(keyWord);
        } else return null;
    }

    /**
     * 这是用于在查看答题状态时，转换显示problem的id或title
     * @param ids
     * @return 返回一个id-title的键值对Map
     */
    @Override
    public Map<Long, String> getProblemIdToTitleMapByProblemIds(List<Integer> ids) {
        List<Map<Object, Object>> problemMaps = problemDao.getProblemTitleByIDs(ids);
        Map<Long, String> resultMap = new HashMap<>();
        for (Map<Object, Object> map: problemMaps) {
            resultMap.put((Long) map.get("id"), (String)map.get("title"));
        }
        return resultMap;
    }

    @Override
    @Transactional
    public boolean insertProblem(Problem problem) {
        boolean result = true;
//        插入题目
        if (problemDao.insertProblem(problem) != 1) {
            result = false;
        }
//        插入题目标签
        if (result == true) {
            for (ProblemLabel problemLabel : problem.getProblemLabels()) {
                result = problemLabelService.insertProblemLabel(problemLabel);
            }
        }
//        插入题目通过率（初始化工作）
        if (result == true) {
            ProblemRatio problemRatio = new ProblemRatio();
            problemRatio.setAcTime(0);
            problemRatio.setSubmitTime(0);
            problemRatio.setRatioValue(0.0);
            problemRatio.setProblemId(problem.getId());
            result = problemRatioService.insertProblemRatio(problemRatio);
        }
        return result;
    }

    @Override
    @Transactional
    public boolean updateProblem(Problem problem) {
        boolean result = true;
        if (problemDao.updateProblem(problem) != 1)
            result = false;
        if (result == true) {
            List<ProblemLabel> oldProblemLabels = problemLabelService.getProblemLabelByProblemId(problem.getId());
            List<ProblemLabel> newProblemLabels = problem.getProblemLabels();
            for (ProblemLabel oldProblemLabel : oldProblemLabels) {
                for (ProblemLabel newProblemLabel : newProblemLabels) {
                    if (oldProblemLabel.getId().equals(newProblemLabel.getId())) {
                        if (!oldProblemLabel.getLabel().equals(newProblemLabel.getLabel()))
//                            如果新旧标签组中同时存在同一个标签（id相同）
//                            但此时该ProblemLabel中的label不相同
//                            那么应该更新该ProblemLabel（以新的为主）
                            result = problemLabelService.updateProblemLabel(newProblemLabel);
                        break;
                    }
                }
            }
            for (ProblemLabel newProblemLabel : newProblemLabels) {
                boolean contains = false;
                for (ProblemLabel oldProblemLabel : oldProblemLabels) {
                    if (oldProblemLabel.getLabel().equals(newProblemLabel.getLabel())) {
                        contains = true;
                        break;
                    }
                }
//                如果旧的标签不包含某个新标签
//                说明该新标签需要被插入
                if (contains == false)
                    result = problemLabelService.insertProblemLabel(newProblemLabel);
            }
            for (ProblemLabel oldProblemLabel : oldProblemLabels) {
                boolean contains = false;
                for (ProblemLabel newProblemLabel : newProblemLabels) {
                    if (newProblemLabel.getLabel().equals(oldProblemLabel.getLabel())) {
                        contains = true;
                        break;
                    }
                }
//                如果新标签不包含某个旧标签
//                说明该旧标签需要被删除
                if (contains == false)
                    result = problemLabelService.deleteProblemLabelByProblemLabelId(oldProblemLabel.getId());
            }
        }
        if (result == true) {
            result = problemRatioService.updateProblemRatio(problem.getProblemRatio());
        }
        return result;
    }

    @Override
    @Transactional
    public boolean deleteProblemByProblemId(Integer problemId) {
        boolean result = true;
        if (problemDao.deleteProblemById(problemId) != 1) {
            result = false;
        }
        if (result == true) {
            result = problemLabelService.deleteProblemLabelByProblemId(problemId);
        }
        if (result == true) {
            result = problemRatioService.deleteProblemRatio(new IDParam(-1, problemId, -1, -1));
        }
        return result;
    }
}
