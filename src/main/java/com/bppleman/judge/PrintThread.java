package com.bppleman.judge;

import com.bppleman.delegate.PrintThreadDelegate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by BppleMan on 2017/11/21.
 */
public class PrintThread extends Thread {
    private Scanner scanner;
    private String compileInfo = "";
    public PrintThread(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        String line = "";
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (null != line && !"".equals(line.trim()) && line != "null")
                compileInfo += (line + "\n");
        }
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }
}
