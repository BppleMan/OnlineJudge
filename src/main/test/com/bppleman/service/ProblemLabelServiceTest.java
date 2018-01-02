package com.bppleman.service;

import com.bppleman.entity.Problem;
import com.bppleman.entity.ProblemLabel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")

public class ProblemLabelServiceTest {

    @Resource
    private ProblemLabelService problemLabelService;

    @Resource
    private ProblemService problemService;

    @Resource
    private LabelService labelService;

    @Before
    public void setUp() throws Exception {
        Logger.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void initProblemLabel() throws Exception {
        List<String> labelValues = labelService.getLabelValues();
        Random random = new Random();
        List<Problem> problems = problemService.getAllProblems();
        for (Problem problem : problems) {
            List<ProblemLabel> problemLabels = problem.getProblemLabels() == null ? new ArrayList<>() : problem.getProblemLabels();
            for (int i = 0; i < 3; i++) {
                ProblemLabel problemLabel = new ProblemLabel();
                problemLabel.setProblemId(problem.getId());
                while (true) {
                    boolean contains = false;
                    problemLabel.setLabel(labelValues.get(random.nextInt(labelValues.size())));
                    for (ProblemLabel label : problemLabels) {
                        if (label.getLabel().equals(problemLabel.getLabel())) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains)
                        break;
                }
                problemLabels.add(problemLabel);
            }
            problem.setProblemLabels(problemLabels);
            problemService.updateProblem(problem);
        }
    }

    @Test
    public void getProblemLabelByProblemId() throws Exception {
        List<ProblemLabel> problemLabels = problemLabelService.getProblemLabelByProblemId(10001);
        System.out.println(problemLabels);
    }

    @Test
    public void getProblemsByLabelValueWithPage() throws Exception {
        List<Problem> problems = problemLabelService.getProblemsByLabelValueWithPage("循环", 1, 10);
        System.out.println(problems);
    }

    @Test
    public void getLabelToProblemsMapByLabelValueWithPage() throws Exception {
//        Map<String, List<Problem>> labelToProblemsMap = problemLabelService.getLabelProblemsMapByLabelValueWithPage(2, 10);
//        System.out.println(labelToProblemsMap);
    }

    @Test
    public void insertProblemLabel() throws Exception {
    }

    @Test
    public void updateProblemLabel() throws Exception {
    }

    @Test
    public void deleteProblemLabelByProblemId() throws Exception {
    }

    @Test
    public void deleteProblemLabelByProblemLabelId() throws Exception {
    }

}