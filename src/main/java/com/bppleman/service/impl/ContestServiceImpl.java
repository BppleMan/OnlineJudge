package com.bppleman.service.impl;

import com.bppleman.controller.ContestController;
import com.bppleman.dao.ContestDao;
import com.bppleman.entity.*;
import com.bppleman.service.ContestService;
import com.bppleman.service.CountService;
import com.bppleman.service.ProblemRatioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
@Service("contestService")
public class ContestServiceImpl implements ContestService {

    @Resource
    private ContestDao contestDao;

    @Resource
    private CountService countService;

    @Resource
    private ProblemRatioService contestProblemRatioService;

    @Override
    public List<Contest> getAllContests() {
        return contestDao.getAllContests();
    }

    @Override
    public List<Contest> getContestsWithPage(String type, String keyWord, Integer page, Integer length) {
        Integer offset = 0;
        if (page == null || length == null)
            offset = 0;
        else offset = (page - 1) * length;
        if ((type == null || "".equals(type.trim()))&& (keyWord == null || "".equals(type.trim()))) {
            return getContestsByParamWithPage(type, keyWord, offset, length);
        } else if (type.equals(ContestController.Type.NAME)) {
            return getContestByNameWithPage(keyWord, offset, length);
        } else  {
            return getContestsByParamWithPage(type, keyWord, offset, length);
        }
    }

    public List<Contest> getContestsByParamWithPage(String columnName, String param, Integer offset, Integer length) {
        return contestDao.getContestsByParamWithPage(columnName, param, offset, length);
    }

    public List<Contest> getContestByNameWithPage(String name, Integer offset, Integer length) {
        return contestDao.getContestByNameWithPage(name, offset, length);
    }

    @Override
    public Contest getContestById(Integer contestId) {
        return contestDao.getContestById(contestId);
    }

    @Override
    public Integer getCount(String type, String keyWord) {
        if ((type == null || "".equals(type.trim())) && (keyWord == null || "".equals(type.trim()))) {
            return countService.getCount(null, "contest", null, null);
        } else if (type.equals(ContestController.Type.NAME)) {
            return countService.getCount(CountService.LIKE, "contest", type, keyWord);
        } else if (type.equals(ContestController.Type.PROBLEM)) {
            return countService.getCount(CountService.EQUAL, "contest_problem", type, keyWord);
        } else {
            return countService.getCount(CountService.EQUAL, "contest", type, keyWord);
        }
    }

    @Override
    @Transactional
    public boolean insertContest(Contest contest) {
        boolean result = true;
        if (contestDao.insertContest(contest) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean updateContest(Contest contest) {
        boolean result = true;
        if (contestDao.updateContest(contest) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean canCreateContest(User user) {
        if (user.getType().equals(User.Admin)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Integer> getProblemIdsByContestId(Integer contestId) {
        return contestDao.getProblemIdsByContestId(contestId);
    }

    @Override
    public List<Problem> getProblemsByContestId(Integer contestId, Integer page, Integer length) {
        Integer offset;
        if (length == null || page == null) {
            offset = 0;
            length = null;
        }
        else
            offset = (page - 1) * length;
        return contestDao.getProblemsByContestId(contestId, offset, length);
    }

    @Override
    public boolean updateContestProblem(Integer contestId, List<Integer> selectedProblemIds) {
        boolean result = true;
//        原有的problemId
        List<Integer> problemIds = contestDao.getProblemIdsByContestId(contestId);
//            经过编辑contest之后，将会产生三部分problem
//            1、需要保留的problem
//            2、需要新增的problem
//            3、需要删除的problem
        List<Integer> shouldInsertProblemIds = new ArrayList<>();
        List<Integer> shouldDeleteProblemIds = new ArrayList<>();
        if (problemIds == null || problemIds.size() == 0) {
//            如果原数组为空，选择的数组非空
//            需要插入全部selectedProblemIds
            if (selectedProblemIds != null && selectedProblemIds.size() != 0) {
                for (Integer selectedProblemId : selectedProblemIds) {
                    shouldInsertProblemIds.add(selectedProblemId);
                }
            }
        } else if (problemIds != null && problemIds.size() != 0) {
//            如果原数组非空，选择的数组为空
//            需要删除全部problemIds
            if (selectedProblemIds == null || selectedProblemIds.size() == 0) {
                for (Integer problemId : problemIds) {
                    shouldDeleteProblemIds.add(problemId);
                }
            }
        } else if (problemIds != null && problemIds.size() != 0) {
            if (selectedProblemIds != null && selectedProblemIds.size() != 0) {
//                如果原数组非空，选择的数组非空
                for (Integer problemId : problemIds) {
//                    如果选中的problemId不包含某个原有的problemId
                    if (!selectedProblemIds.contains(problemId)) {
//                        说明该原有problemId是需要删除的
                        shouldDeleteProblemIds.add(problemId);
                    }
                }
                for (Integer selectedProblemId : selectedProblemIds) {
//                    如果原有problemID不包含某个选中的problemId
                    if (!problemIds.contains(selectedProblemId)) {
//                        说明该选中problemId是需要新增的
                        shouldInsertProblemIds.add(selectedProblemId);
                    }
                }
            }
        }

//        插入problem
        for (Integer shouldInsertProblemId : shouldInsertProblemIds) {
            result = insertProblemToContest(contestId, shouldInsertProblemId);
        }

//        删除problem
        for (Integer shouldDeleteProblemId : shouldDeleteProblemIds) {
            result = deleteProblemFromContest(contestId, shouldDeleteProblemId);
        }
        return result;
    }

    @Transactional
    public boolean insertProblemToContest(Integer contestId, Integer problemId) {
        boolean result = true;
        if (contestDao.insertProblemToContest(contestId, problemId) != 1)
            result = false;
        if (result) {
            ProblemRatio problemRatio = new ProblemRatio();
            problemRatio.setContestId(contestId);
            problemRatio.setProblemId(problemId);
            result = contestProblemRatioService.insertProblemRatio(problemRatio);
        }
        return result;
    }

    @Transactional
    public boolean deleteProblemFromContest(Integer contestId, Integer problemId) {
        boolean result = true;
        if (contestDao.deleteProblemFromContest(contestId, problemId) != 1)
            result = false;
        if (result) {
            result = contestProblemRatioService.deleteProblemRatio(new IDParam(-1, problemId, contestId, -1));
        }
        return result;
    }
}
