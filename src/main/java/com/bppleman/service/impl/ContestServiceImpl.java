package com.bppleman.service.impl;

import com.bppleman.dao.ContestDao;
import com.bppleman.dao.UserDao;
import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import com.bppleman.entity.User;
import com.bppleman.service.ContestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
@Service("contestService")
public class ContestServiceImpl implements ContestService {

    @Resource
    private ContestDao contestDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<Contest> getAllContests() {
        return contestDao.getAllContests();
    }

    @Override
    public List<Contest> getContestsByNote(String note) {
        return contestDao.getContestsByNote(note);
    }

    @Override
    public List<Contest> getContestsByAuthor(String author) {
        return contestDao.getContestsByAuthor(author);
    }

    @Override
    public List<Contest> getContestsByType(String type) {
        return contestDao.getContestsByType(type);
    }

    @Override
    public List<Contest> getContestsByStatus(String status) {
        return contestDao.getContestsByStatus(status);
    }

    @Override
    public Contest getContestByName(String name) {
        return contestDao.getContestByName(name);
    }

    @Override
    public Contest getContestById(int id) {
        return contestDao.getContestById(id);
    }

    @Override
    public List<Contest> getProblemsByKeyWord(String keyword) {
        return contestDao.getProblemsByKeyWord(keyword);
    }

    @Override
    public List<Problem> getProblemsByContestID(int id) {
        return contestDao.getProblemsByContestID(id);
    }

    @Override
    public List<Problem> getProblemsByLabel(String label) {
        return contestDao.getProblemsByLabel(label);
    }

    @Override
    public boolean insertContest(Contest contest) {
        if (contestDao.insertContest(contest) == 1)
            return true;
        return false;
    }

    @Override
    public boolean canCreateContest(User user) {
        if (user.getType().equals(User.Admin)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertContestProblem(int contestId, List<Integer> problemIds) {
        if (contestDao.insertContestProblem(contestId, problemIds) > 0)
            return true;
        return false;
    }

    @Override
    public boolean updateContestProblem(int contestId, List<Integer> problemIds) {
        contestDao.deleteContestProblem(contestId);
        contestDao.insertContestProblem(contestId, problemIds);
        return true;
    }
}
