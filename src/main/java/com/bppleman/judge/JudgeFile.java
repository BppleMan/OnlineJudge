package com.bppleman.judge;

import com.bppleman.entity.Data;
import com.bppleman.entity.Status;
import com.bppleman.service.DataService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by BppleMan on 2017/11/21.
 */
public class JudgeFile {

    private Status status;
    private final String rootPath = "/Users/BppleMan/Desktop/OnlineJudge";
    private String userPath = null;
    private String problemPath = null;
    private String resourcePath = null;
    private String codeFilePath = null;
    private String inputFilePath = null;
    private String answerFilePath = null;
    private String shellFilePath = null;

    private DataService dataService = null;

    public JudgeFile(Status status, DataService dataService) {
        this.status = status;
        this.dataService = dataService;
        problemPath = rootPath + "/" + status.getIdParam().getProblemId();
        userPath = problemPath + "/" + status.getIdParam().getProblemId();
        resourcePath = userPath + "/resource";
        if (status.getCode().getLanguage().toLowerCase().equals("java")) {
            codeFilePath = resourcePath + "/Main." + status.getCode().getLanguage().toLowerCase();
        } else {
            codeFilePath = resourcePath + "/code." + status.getCode().getLanguage().toLowerCase();
        }
        inputFilePath = resourcePath + "/in";
        answerFilePath = resourcePath + "/answer";
    }

    public void makeDirector() {
        File problemDir = new File(problemPath);
        if (!problemDir.exists()) {
            problemDir.mkdir();
        }
        File userDir = new File(userPath);
        if (!userDir.exists()) {
            userDir.mkdir();
        }
        File resourceDir = new File(resourcePath);
        if (!resourceDir.exists()) {
            resourceDir.mkdir();
        }
        resourceDir.mkdir();
    }

    public boolean codeToFile() {
        File codeFile = new File(codeFilePath);
        File inputFile = new File(inputFilePath);
        File answerFile = new File(answerFilePath);
        File shellFile = null;
        BufferedWriter bw = null;
        try {
            Data data = dataService.getDataByProblemIDAndShellName(status.getIdParam().getProblemId(),
                    status.getCode().getLanguage().toLowerCase() + ".sh");
            shellFilePath = resourcePath + "/" + data.getShellName();
            shellFile = new File(shellFilePath);
            bw = new BufferedWriter(new FileWriter(codeFile));
            bw.write(status.getCode().getCodeValue());
            bw.flush();
            bw.close();
            bw = new BufferedWriter(new FileWriter(inputFile));
            bw.write(data.getInput());
            bw.flush();
            bw.close();
            bw = new BufferedWriter(new FileWriter(answerFile));
            bw.write(data.getAnswer());
            bw.flush();
            bw.close();
            bw = new BufferedWriter(new FileWriter(shellFile));
            bw.write(data.getShellValue());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getShellFilePath() {
        return shellFilePath;
    }

    public void setShellFilePath(String shellFilePath) {
        this.shellFilePath = shellFilePath;
    }

    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }
}
