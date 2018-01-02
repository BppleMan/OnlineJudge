package com.bppleman.dao;

import com.bppleman.entity.Contest;
import com.bppleman.entity.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BppleMan on 2017/11/22.
 */
@Repository
public interface ContestDao {
    /******************************************************
     * 针对contest的CRUD
     ******************************************************/

    //    获取所有contest
    List<Contest> getAllContests();
    List<Contest> getContestsByParamWithPage(String columnName, String param, Integer offset, Integer length);
    //    通过名称获取contest
    List<Contest> getContestByNameWithPage(String name, Integer offset, Integer length);
    //    通过contestId获取contest
    Contest getContestById(Integer contestId);
    //    插入一个contest
    Integer insertContest(Contest contest);
//    更新Contest
    Integer updateContest(Contest contest);

    /******************************************************
     * 针对contest中problem的CRUD
     ******************************************************/

    //    通过contestId获取contest中的problemIds
    List<Integer> getProblemIdsByContestId(Integer contestId);
    //    通过contestId获取contest中的problems
    List<Problem> getProblemsByContestId(Integer contestId, Integer offset, Integer length);
    //    将题目插入contest中
    Integer insertProblemToContest(Integer contestId, Integer problemId);
    //    将题目从contest中删除
    Integer deleteProblemFromContest(Integer contestId, Integer problemId);
}
