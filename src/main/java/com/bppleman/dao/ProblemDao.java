package com.bppleman.dao;

import com.bppleman.entity.Problem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BppleMan on 2017/11/8.
 */
@Repository
public interface ProblemDao extends Serializable{
    List<Problem> getAllProblems();
    Problem getProblemByID(int id);
    List<Problem> getProblemsWithPage(int offset, int length);
    List<Problem> getProblemsByAuthorWithPage(String author, int offset, int length);
    List<Problem> getProblemsByTitleWithPage(String title, int offset, int length);
    List<Problem> getProblemsByLabelWithPage(List<String> labels, int offset, int length);
    Integer getCount();
    Integer getCountByAuthor(String author);
    Integer getCountByTitle(String title);
    Integer getCountByLabel(List<String> labels);
    List<Map<Object, Object>> getProblemTitleByIDs(List<Integer> ids);
    int insertProblem(Problem problem);
    int insertProblemLabels(int problemId, List<String> labels);
    int deleteProblemById(int problemId);
    int deleteProblemLabelsByProblemId(int problemId);
}
