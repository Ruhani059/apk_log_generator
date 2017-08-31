package com.ruhani;

import org.junit.Before;
import org.junit.Test;

public class FileManagementTest {

    String directoryName;
    @Before
    public void setUp() throws Exception {
        directoryName = "F:\\test-imo\\apk-src";
    }

    @Test
    public void TestFunction() throws Exception{
        System.out.println(new FileManagement(directoryName).getFileListSize());
    }


}
//package test;
//
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
//
//public class TesterMain {
//
//    public static void main(String[] args)
//    {
//        Result result = JUnitCore.runClasses(ModifySmaliFileTest.class);
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//    }
//}
