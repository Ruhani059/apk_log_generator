package com.ruhani;

import java.io.File;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Welcome To Log Insertion in Smali.");
        String smaliSource = "F:\\test-imo\\apk-src\\smali";
        if(args.length != 0){
            smaliSource = args[0];
        }
        FileManagement fileManagement = new FileManagement(smaliSource);
        for (File file : fileManagement.getFileList())
        {
//            System.out.println(file.getAbsolutePath());
            new ModifySmaliFile(file.getAbsolutePath());
        }
        System.out.println(fileManagement.toString());
    }

    public static <T> void log(T string)
    {
//        System.out.println(string);
    }

    public static void Main(String[] args) {
        System.out.println("Searching...");
        //argument 1 : Search Keyword
        //argument 2 : file location
        //argument 3 : jani na
        
        String parentDirectoryLocation = null;
        String searchKey = null;

        if(args.length == 0){
            searchKey = "";
            parentDirectoryLocation = new File("").getAbsolutePath();
        }

        else if(args.length == 1){
            searchKey = args[0];
//            parentDirectoryLocation = "F:\\Apk Decompile\\apk to java\\decompiled.zip\\viber";
            parentDirectoryLocation = new File("").getAbsolutePath();
        }

        else if(args.length >= 2){
            searchKey = args[0];
            parentDirectoryLocation = args[1];
        }

//        File file = new File("");
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.isDirectory());

        FileManagement test = new FileManagement(parentDirectoryLocation);
        test.getAllFiles();
//        test.searchAllFiles(searchKey);
        System.out.println("Total Files "+test.FileListSize+".");
    }
}
