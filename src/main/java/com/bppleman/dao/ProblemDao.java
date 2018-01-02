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
    Problem getProblemByProblemId(Integer id);
    List<Problem> getProblemsWithPage(Integer offset, Integer length);
    List<Problem> getProblemsByAuthorWithPage(String author, Integer offset, Integer length);
    List<Problem> getProblemsByTitleWithPage(String title, Integer offset, Integer length);
    List<Map<Object, Object>> getProblemTitleByIDs(List<Integer> ids);
    Integer insertProblem(Problem problem);
    Integer updateProblem(Problem problem);
    Integer deleteProblemById(Integer problemId);
}
