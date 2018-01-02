package com.bppleman.service.impl;

import com.bppleman.dao.ProblemUserSolveDao;
import com.bppleman.entity.IDParam;
import com.bppleman.entity.Problem;
import com.bppleman.entity.UserSolve;
import com.bppleman.service.CountService;
import com.bppleman.service.ProblemUserSolveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("problemUserSolveService")
public class ProblemUserSolveServiceImpl implements ProblemUserSolveService {

    @Resource
    private ProblemUserSolveDao problemUserSolveDao;

    @Resource
    private CountService countService;

    public List<UserSolve> getUserSolve(Integer userId) {
        return problemUserSolveDao.getUserSolve(userId);
    }

    @Override
    public Map<Integer, String> getUserSolveMap(Integer userId) {
        List<UserSolve> userSolves = getUserSolve(userId);
        Map<Integer, String> result = new HashMap<>();
        for (UserSolve us: userSolves) {
            result.put(us.getIdParam().getProblemId(), us.getSolveState());
        }
        return result;
    }

    @Override
    public UserSolve getUserSolveByParam(Integer userId, Integer problemId) {
        return problemUserSolveDao.getUserSolveByParam(userId, problemId);
    }

    @Override
    public List<Problem> getProblemByUserSolved(Integer page, Integer length, Integer userId) {
        Integer offset = 0;
        if (page == null || length == null) {
            offset = 0;
        } else {
            offset = (page - 1) * length;
        }
        List<Problem> userSolvedProblems = problemUserSolveDao.getProblemByUserSolveInfo(UserSolve.SOLVED, offset, length, userId);
        return userSolvedProblems;
    }

    @Override
    public List<Problem> getProblemByUserUnSolved(Integer page, Integer length, Integer userId) {
        Integer offset = 0;
        if (page == null || length == null) {
            offset = 0;
        } else {
            offset = (page - 1) * length;
        }
        List<Problem> userUnSolvedProblems = problemUserSolveDao.getProblemByUserSolveInfo(UserSolve.UN_SOLVED, offset, length, userId);
        return userUnSolvedProblems;
    }

    @Override
    public Map<String, List<Problem>> getUserSolveProblemsMap(Map<String, Integer> userSolveCurrentPageMap, Integer length, Integer userId) {
        Map<String, List<Problem>> userSolveProblemsMap = new HashMap<>();
        for (String key : userSolveCurrentPageMap.keySet()) {
            Integer offset, page;
            if (length == null) {
                offset = 1;
            } else {
                page = userSolveCurrentPageMap.get(key);
                offset = (page - 1) * length;
            }
            List<Problem> problems = problemUserSolveDao.getProblemByUserSolveInfo(key, offset, length, userId);
            userSolveProblemsMap.put(key, problems);
        }
        return userSolveProblemsMap;
    }

    @Override
    public Integer getUserSolvedTotalPage(Integer userId, Double countPerPage) {
        String tableName = "problem_user_solve";
        Map<String, String> args = new HashMap<>();
        args.put("user_id", String.valueOf(userId));
        args.put("solve_state", UserSolve.SOLVED);
        return (int) Math.ceil((double) countService.getCount(CountService.EQUAL, tableName, args) / countPerPage);
    }

    @Override
    public Integer getUserUnSolvedTotalPage(Integer userId, Double countPerPage) {
        String tableName = "problem_user_solve";
        Map<String, String> args = new HashMap<>();
        args.put("user_id", String.valueOf(userId));
        args.put("solve_state", UserSolve.UN_SOLVED);
        return (int) Math.ceil((double) countService.getCount(CountService.EQUAL, tableName, args) / countPerPage);
    }

    @Override
    public Map<String, Integer> getUserSolveTotalPageMap(Integer userId, Double countPerPage) {
        Map<String, Integer> userSolveTotalPageMap = new HashMap<>();
        userSolveTotalPageMap.put(UserSolve.SOLVED, getUserSolvedTotalPage(userId, countPerPage));
        userSolveTotalPageMap.put(UserSolve.UN_SOLVED, getUserUnSolvedTotalPage(userId, countPerPage));
        return userSolveTotalPageMap;
    }

    @Override
    public List<Integer> getUserSubmittedProblemIds(Integer userId) {
        return problemUserSolveDao.getUserSubmittedProblemIds(userId);
    }

    @Override
    public boolean insertUserSolve(UserSolve userSolve) {
        boolean result = true;
        UserSolve temp = getUserSolveByParam(userSolve.getIdParam().getUserId(), userSolve.getIdParam().getProblemId());
        if (temp != null) {
            if (temp.getSolveState().equals(UserSolve.UN_SOLVED)) {
                if (problemUserSolveDao.updateUserSolve(userSolve) != 1)
                    result = false;
            }
        } else {
            if (problemUserSolveDao.insertUserSolve(userSolve) != 1)
                result = false;
        }
        return result;
    }

    @Override
    public boolean updateUserSolve(UserSolve userSolve) {
        boolean result = true;
        if (problemUserSolveDao.updateUserSolve(userSolve) != 1)
            result = false;
        return result;
    }

    @Override
    public boolean deleteUserSolve(Integer userId, Integer problemId) {
        boolean result = true;
        if (problemUserSolveDao.deleteUserSolve(userId, problemId) != 0)
            result = false;
        return result;
    }
}
