package com.bppleman.judge;

import com.bppleman.delegate.PrintThreadDelegate;
import com.bppleman.entity.Status;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BppleMan on 2017/11/21.
 */
public class JudgeCore {
    private String compileInfo;

    public void runShell(String command) {
        try {
            Process process =  Runtime.getRuntime().exec(command);
            PrintThread inputStream = new PrintThread(process.getInputStream());
            inputStream.start();
            PrintThread errorStream = new PrintThread(process.getErrorStream());
            errorStream.start();
            int value = process.waitFor();
            compileInfo = inputStream.getCompileInfo();
            compileInfo += errorStream.getCompileInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String compareAnswer(String resourcePath) {
        String result = Status.Wrong_Answer;
        File answerFile = new File(resourcePath + "/answer");
        File outFile = new File(resourcePath + "/out");
        StringBuffer answer = null;
        StringBuffer out = null;
        Scanner sc = null;
        try {
            sc = new Scanner(answerFile);
            answer = new StringBuffer();
            while (sc.hasNext()) {
                answer.append(sc.nextLine() + "\n");
            }
            sc.close();
            sc = new Scanner(outFile);
            out = new StringBuffer();
            while (sc.hasNext()) {
                out.append(sc.nextLine() + "\n");
            }
            if (!answer.toString().equals(out.toString())) {
                result = Status.Wrong_Answer;
                sc = new Scanner(answerFile);
                answer = new StringBuffer();
                while (sc.hasNext()) {
                    answer.append(sc.next());
                }
                sc.close();
                sc = new Scanner(outFile);
                out = new StringBuffer();
                while (sc.hasNext()) {
                    out.append(sc.next());
                }
                if (answer.toString().equals(out.toString())) {
                    result = Status.Presentation_Error;
                }
            } else {
                result = Status.Accepted;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int  calculatorTime(String resourcePath) {
        int result = -1;
        String time = getInfo(resourcePath, "/time");
        String regex = "real\t\\dm\\d\\.\\d\\d\\ds";
        String str1 = match(time, regex);
        if (str1 != null) {
            regex = "\\d\\.\\d\\d\\d";
            String str2 = match(str1, regex);
            return (int)(Double.parseDouble(str2) * 1000.0);
        } else {
            System.out.println("NO MATCH");
        }
        return result;
    }

    private String match(String string, String regex) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(string);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

    public int calculatorMemory(String resourcePath) {
        int result = -1;
        String info = getInfo(resourcePath, "/info");
        String regex = "memory:\\s+\\d+\\s";
        String str1 = match(info.toString(), regex);
        if (str1 != null) {
            regex = "\\d+";
            String str2 = match(str1, regex);
            return Integer.parseInt(str2);
        } else {
            System.out.println("NO MATCH");
        }
        return result;
    }

    private String getInfo(String resourcePath, String args) {
        File infoFile = new File(resourcePath + args);
        StringBuffer info = new StringBuffer();
        try {
            Scanner sc = new Scanner(infoFile);
            while (sc.hasNext()) {
                info.append(sc.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return info.toString();
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }
}
