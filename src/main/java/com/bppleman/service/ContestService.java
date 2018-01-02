package com.bppleman.service;

import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import com.bppleman.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
//@Repository
public interface ContestService {
    List<Contest> getAllContests();
    List<Contest> getContestsWithPage(String type, String keyWord, Integer page, Integer length);
    Contest getContestById(Integer contestId);

    /**
     * 返回符合某条件的查询得到的记录条数
     * @param type 如果查询contest，那么type需要填写contest表中的某个列名，主要从
     * @param keyWord
     * @return
     */
    Integer getCount(String type, String keyWord);
    boolean insertContest(Contest contest);
    boolean updateContest(Contest contest);

    boolean canCreateContest(User user);
    List<Integer> getProblemIdsByContestId(Integer contestId);
    List<Problem> getProblemsByContestId(Integer contestId, Integer page, Integer length);
    boolean updateContestProblem(Integer contestId, List<Integer> selectedProblemIds);
}
