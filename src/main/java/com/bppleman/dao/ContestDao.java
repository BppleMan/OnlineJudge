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
//    获取所有contest
    List<Contest> getAllContests();
//    通过备注获取contest
    List<Contest> getContestsByNote(String note);
//    通过作者获取contest
    List<Contest> getContestsByAuthor(String author);
//    通过类型获取contest
    List<Contest> getContestsByType(String type);
//    通过状态获取contest
    List<Contest> getContestsByStatus(String status);
//    通过关键字获取contest
    List<Contest> getProblemsByKeyWord(String keyword);
//    通过contestId获取题目
    List<Problem> getProblemsByContestID(int id);
//    通过标签获取题目
    List<Problem> getProblemsByLabel(String label);
//    通过名称获取contest
    Contest getContestByName(String name);
//    通过id获取contest
    Contest getContestById(int id);
//    插入一个contest
    int insertContest(Contest contest);
//    将题目插入contest中
    int insertContestProblem(int contestId, List<Integer> problemIds);
//    将题目从contest中删除
    int deleteContestProblem(int contestId);
}
