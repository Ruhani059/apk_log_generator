package com.ruhani;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class LogAnalysisTest {

    String logFileName;
    String logDirectoryName;

    @Before
    public void setUp() throws Exception {
        logFileName = "F:\\test-imo\\log\\all_log_inteserted_call_2.txt";
        logDirectoryName = "F:\\test-imo\\log";
    }

    @Test
    public void testLogAnalysis() throws Exception {
        new LogAnalysis(logFileName);
    }

    @Test
    public void testAllFile() throws Exception {
        FileManagement fileManagement = new FileManagement(logDirectoryName);
        for (File file : fileManagement.getFileList())
        {
            new LogAnalysis(file.getAbsolutePath());
        }
    }
}
