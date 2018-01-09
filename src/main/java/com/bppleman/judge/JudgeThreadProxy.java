package com.bppleman.judge;

import com.bppleman.entity.Status;
import com.bppleman.service.ProblemDataService;
import com.bppleman.service.StatusService;
import com.bppleman.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by BppleMan on 2017/11/19.
 */
@Component
public class JudgeThreadProxy {

    @Resource
    private ProblemDataService problemDataService = null;

    @Resource
    private UserService userService = null;

    private StatusService statusService = null;

    private Status status;

    public JudgeThreadProxy(){}

    public JudgeThreadProxy(Status status) {
        this.status = status;
    }

    public void run() {
        Logger logger = Logger.getRootLogger();
        logger.setLevel(Level.DEBUG);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                创建评测所需要的目录结构，包括目录和文件
                JudgeFile judgeFile = new JudgeFile(status, problemDataService);
//                创建目录
                if (judgeFile.makeDirector())
                    logger.info("->创建目录结构成功");
                else logger.error("->创建目录结构失败");
//                创建文件
                if (judgeFile.codeToFile())
                    logger.info("->创建代码/脚本文件成功");
                else logger.error("->创建目录结构失败");
//                评测机核心部分
                JudgeCore judgeCore = new JudgeCore();
//                对shell文件赋予执行权限
                String command = "chmod u+x " + judgeFile.getShellFilePath();
                judgeCore.runShell(command);
                logger.info("->赋予执行权限成功");
//                运行shell文件
                command = judgeFile.getShellFilePath() + " " + judgeFile.getResourcePath();
                judgeCore.runShell(command);
//                取出编译结果并赋值给status
                String compileInfo = judgeCore.getCompileInfo();
                logger.error("编译结果:" + compileInfo);
                status.setCompileInfo(compileInfo);
                if (compileInfo == null ||
                        compileInfo.equals("") ||
                        compileInfo.trim().equals("") ||
                        compileInfo.trim().equals(status.getCode().getLanguage()+"ubuntu")) {
//                比较运行结果并赋值给status
                    String value = judgeCore.compareAnswer(judgeFile.getResourcePath());
                    status.setStatusValue(value);
//                计算所耗时间
                    Integer time = judgeCore.calculatorTime(judgeFile.getResourcePath());
                    status.setTime(time);
//                计算所耗内存
                    Integer memory = judgeCore.calculatorMemory(judgeFile.getResourcePath());
                    status.setMemory(memory);

//                删除临时文件
                    JudgeFile.deleteAllFilesOfDir(new File(judgeFile.getResourcePath()));
                    logger.info("删除临时文件成功");
                } else {
                    status.setStatusValue(Status.Compilation_Error);
                }
//                更新status表
                statusService.updateStatus(status);

                logger.setLevel(Level.ERROR);
            }
        });
        thread.start();
//        junit需要使用下面语句对主线程进行阻塞
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusService getStatusService() {
        return statusService;
    }

    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }
}
