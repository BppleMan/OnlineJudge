package com.bppleman.service.impl;

import com.bppleman.controller.ProblemController;
import com.bppleman.dao.ProblemDao;
import com.bppleman.dao.ProblemRatioDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemRatio;
import com.bppleman.service.ProblemService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
    private ProblemDao problemDao = null;

    @Resource
    private ProblemRatioDao problemRatioDao = null;

    @Resource
    private DataSourceTransactionManager transactionManager = null;

    @Override
    public List<Problem> getAllProblems() {
        return problemDao.getAllProblems();
    }

    @Override
    public List<Problem> getProblemsWithPage(String type, String keyWord, int page, int length) {
        int offset = (page - 1) * length;
        if ((type == null || "".equals(type.trim()))&& (keyWord == null || "".equals(type.trim()))) {
            return problemDao.getProblemsWithPage(offset, length);
        } else if (type.equals(ProblemController.Type.ID)) {
            int id = Integer.parseInt(keyWord);
            List<Problem> problems = new ArrayList<>();
            problems.add(getProblemByID(id));
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
    public Problem getProblemByID(int id) {
        return problemDao.getProblemByID(id);
    }

    private List<Problem> getProblemsByAuthorWithPage(String author, int offset, int length) {
        return problemDao.getProblemsByAuthorWithPage(author, offset, length);
    }

    private List<Problem> getProblemsByTitleWithPage(String title, int offset, int length) {
        return problemDao.getProblemsByTitleWithPage(title, offset, length);
    }

    private List<Problem> getProblemsByLabelWithPage(String tags, int offset, int length) {
        List<String> labels = new ArrayList<>();
        String []temp = tags.split(",\\s*");
        for (int i = 0; i < temp.length; i++) {
            labels.add(temp[i]);
        }
        return problemDao.getProblemsByLabelWithPage(labels, offset, length);
    }

    @Override
    public int getCount(String type, String keyWord) {
        if ((type == null || "".equals(type.trim()))&& (keyWord == null || "".equals(type.trim()))) {
            return problemDao.getCount();
        } else if (type.equals(ProblemController.Type.ID)) {
            return 1;
        }
        else if (type.equals(ProblemController.Type.AUTHOR)) {
            return problemDao.getCountByAuthor(keyWord);
        } else if (type.equals(ProblemController.Type.TITLE)){
            return problemDao.getCountByTitle(keyWord);
        } else {
            List<String> labels = new ArrayList<>();
            String []temp = keyWord.split(",\\s*");
            for (int i = 0; i < temp.length; i++) {
                labels.add(temp[i]);
            }
            return problemDao.getCountByLabel(labels);
        }
    }

    @Override
    public Map<Long, String> getIDTitleMapByIDs(List<Integer> ids) {
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
        if (problemDao.insertProblem(problem) == 1) {
            problemDao.insertProblemLabels(problem.getId(), problem.getLabels());
            ProblemRatio problemRatio = new ProblemRatio();
            problemRatio.setIdParam(new IDParam(null, problem.getId(), null, null));
            problemRatioDao.insertProblemRatio(problemRatio);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteProblemByID(int problemId) {
        if (problemDao.deleteProblemById(problemId) == 1) {
            problemDao.deleteProblemLabelsByProblemId(problemId);
            List<IDParam> idParams = new ArrayList<>();
            idParams.add(new IDParam(null, problemId, null, null));
            problemRatioDao.deleteProblemRatio(idParams);
            return true;
        }
        return false;
    }
}
