package com.ruhani;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ModifySmaliFIleTeset {

    String fileName;

    @Before
    public void setUp() throws Exception {
        fileName = "F:\\test-imo\\apk-src\\smali\\";
    }

    @Test
    public void testModifySmaliFIle() throws Exception {
        FileManagement fileManagement = new FileManagement(fileName);
        for (File file : fileManagement.getFileList())
        {
            System.out.println(file.getAbsolutePath());
            new ModifySmaliFile(file.getAbsolutePath());
        }
    }
}
