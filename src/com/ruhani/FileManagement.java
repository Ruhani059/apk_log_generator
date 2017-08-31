package com.ruhani;

import java.io.*;
import java.util.ArrayList;

public class FileManagement {
    String parentDirectory;
    ArrayList<File> FileList;
    int FileListSize;
    boolean isDirectory;
    boolean isFile;

    public FileManagement() {
        //Default Directory
        this.parentDirectory = "F:\\test-imo\\imo-test-apktool\\smali";
        this.FileList = new ArrayList<>();
        this.FileListSize = 0;

    }

    public FileManagement(String parentDirectory) {
        this.parentDirectory = parentDirectory;
        this.FileList = new ArrayList<>();
        this.FileListSize = 0;
        this.isDirectory = new File(this.parentDirectory).isDirectory();
        this.isFile = new File(this.parentDirectory).isFile();

        log("Parent Directory is : "+this.parentDirectory);
        this.getAllFiles();
    }

    public FileManagement(String parentDirectory, ArrayList<File> FileList) {
        this.parentDirectory = parentDirectory;
        this.FileList = (ArrayList<File>) FileList.clone();
        this.FileListSize = FileList.size();
    }

    public FileManagement(String parentDirectory, ArrayList<File> FileList, int FileListSize) {
        this.parentDirectory = parentDirectory;
        this.FileList = (ArrayList<File>) FileList.clone();
        this.FileListSize = FileListSize;

    }

    private void findCurrentSubDirectories(File currentDirectory, ArrayList<File>fileList)
    {
        File[] files = currentDirectory.listFiles();
        for (File file : files) {

            //if file then add to the fileArrayList.
            if(file.isFile()){
                fileList.add( file.getAbsoluteFile() );
//                log("File Name : "+file.getAbsolutePath());
            }

            //if current file is a directory then call recursive function
            else if(file.isDirectory()){
                findCurrentSubDirectories(file.getAbsoluteFile(), fileList);
            }
        }

    }

    public void getAllFiles()
    {

        ArrayList<File>fileArrayList = new ArrayList<>();

        if(this.isDirectory)
        {
            findCurrentSubDirectories(new File(this.parentDirectory) , fileArrayList);
        } else if(this.isFile)
        {
            fileArrayList.add(new File(this.parentDirectory));
        }
        else
        {
            System.out.println("Invalid Path : "+this.parentDirectory + "\nDoesn't Exist.");
        }
        this.setFileList(fileArrayList);
        fileArrayList.clear();
    }

    public ArrayList<File> getFileList() {
        return FileList;
    }

    public void setFileList(ArrayList<File> FileList) {
        this.FileList = (ArrayList<File>) FileList.clone();
        this.setFileListSize(this.FileList.size());
    }

    public int getFileListSize() {
        return FileListSize;
    }

    public void setFileListSize(int FileListSize) {
        this.FileListSize = FileListSize;
    }

    public <T> void log(T string)
    {
//        System.out.println(string);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Parent Directory : ").append(this.parentDirectory).append("\n")
                .append("Total Files : ").append(this.getFileListSize());
        return stringBuffer.toString();
    }
}
