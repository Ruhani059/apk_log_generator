package com.ruhani;

import org.junit.Before;
import org.junit.Test;

public class StringConstantsTest {

    public int number;
    public String tag_String;
    public String message_string;

    @Before
    public void setUp() {
        number = 10;
        tag_String = "Ruhani";
        message_string = "Hello World";
    }

    @Test
    public void testCreateMessage(){
        StringConstants test = new StringConstants(number, tag_String,message_string);
        System.out.println( test.fullLogMessage);
    }

}
