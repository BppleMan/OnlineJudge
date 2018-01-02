package com.bppleman.service;

import com.bppleman.entity.Problem;
import com.bppleman.entity.SmartProblem;

import java.util.List;

public interface SmartProblemService {
    List<Problem> getProblemsBySmartProblem(SmartProblem smartProblem, Integer userId);
}
