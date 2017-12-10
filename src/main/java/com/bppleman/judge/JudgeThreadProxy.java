package com.bppleman.judge;

import com.bppleman.delegate.CodeAffairDelegate;
import com.bppleman.entity.Status;
import com.bppleman.service.DataService;
import com.bppleman.service.StatusService;
import com.bppleman.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by BppleMan on 2017/11/19.
 */
@Component
public class JudgeThreadProxy {

    @Resource
    private DataService dataService = null;

    @Resource
    private StatusService statusService = null;

    @Resource
    private UserService userService = null;

    private CodeAffairDelegate  delegate = null;

    private Status status;

    public JudgeThreadProxy(){}

    public JudgeThreadProxy(Status status) {
        this.status = status;
    }

    public void run() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                创建评测所需要的目录结构，包括目录和文件
                JudgeFile judgeFile = new JudgeFile(status, dataService);
//                创建目录
                judgeFile.makeDirector();
//                创建文件
                judgeFile.codeToFile();
//                评测机核心部分
                JudgeCore judgeCore = new JudgeCore();
//                对shell文件赋予执行权限
                String command = "chmod u+x " + judgeFile.getShellFilePath();
                judgeCore.runShell(command);
//                运行shell文件
                command = judgeFile.getShellFilePath() + " " + judgeFile.getResourcePath();
                judgeCore.runShell(command);
//                取出编译结果并赋值给status
                String compileInfo = judgeCore.getCompileInfo();
                status.setCompileInfo(compileInfo);
                if (compileInfo == null || compileInfo.equals("") || compileInfo.trim().equals("")) {
//                比较运行结果并赋值给status
                    String value = judgeCore.compareAnswer(judgeFile.getResourcePath());
                    status.setStatusValue(value);
//                计算所耗时间
                    int time = judgeCore.calculatorTime(judgeFile.getResourcePath());
                    status.setTime(time);
//                计算所耗内存
                    int memory = judgeCore.calculatorMemory(judgeFile.getResourcePath());
                    status.setMemory(memory);

//                删除临时文件
                    JudgeFile.deleteAllFilesOfDir(new File(judgeFile.getResourcePath()));
                } else {
                    status.setStatusValue(Status.Compilation_Error);
                }
//                更新t_status表
//                statusService.updateStatus(status);
                delegate.shouldUpdateStatus(status);
                delegate.shouldUpdateProblemRatio(status);
                delegate.shouldUpdateRank(status);
                delegate.shouldUpdateUserSolve(status);
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

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public CodeAffairDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(CodeAffairDelegate delegate) {
        this.delegate = delegate;
    }
}
