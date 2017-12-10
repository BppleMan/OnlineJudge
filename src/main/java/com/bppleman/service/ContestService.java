package com.bppleman.service;

import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import com.bppleman.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
@Repository
public interface ContestService {
    List<Contest> getAllContests();
    List<Contest> getContestsByNote(String note);
    List<Contest> getContestsByAuthor(String author);
    List<Contest> getContestsByType(String type);
    List<Contest> getContestsByStatus(String status);
    List<Contest> getProblemsByKeyWord(String keyword);
    List<Problem> getProblemsByContestID(int id);
    List<Problem> getProblemsByLabel(String label);
    Contest getContestByName(String name);
    Contest getContestById(int id);
    boolean insertContest(Contest contest);
    boolean canCreateContest(User user);
    boolean insertContestProblem(int contestId, List<Integer> problemIds);
    boolean updateContestProblem(int contestId, List<Integer> problemIds);
}
