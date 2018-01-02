package com.bppleman.dao;

import com.bppleman.entity.Problem;
import com.bppleman.entity.SmartProblem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartProblemDao {
    List<Problem> getProblemsBySmartProblem(SmartProblem smartProblem);
}
