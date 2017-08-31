package com.ruhani;

import java.io.*;
import java.util.regex.Pattern;

public class ModifySmaliFile {
    File file;
    StringBuffer updateFileContent;
    String className;
    String methodName;
    String logMessage;
    String locals;

    public ModifySmaliFile(String fileName)
    {
        this.file = new File(fileName);
        this.updateFileContent = new StringBuffer();
        String extention = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")+1);
        log("File Extension : " + extention);
        if (extention.equals("smali")) {
            readFile();
            log(this.updateFileContent.toString());
            writeToFile();
        }
    }

    private void readFile()
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                this.updateFileContent.append(strLine + "\n");
                log(strLine);

                if(strLine.startsWith(".class"))
                {
                    this.setClassName(strLine);
                }

                else if(strLine.startsWith(".method"))
                {
                    modifyMethod(strLine,bufferedReader);
                }
            }
            bufferedReader.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void modifyMethod(String str, BufferedReader br)
    {
        log("Method Name : public void modifyMethod(String str, BufferedReader br)");

        setMethodName(str); //extract mathod name from first line
        boolean isAbstract = str.contains("abstract");
        boolean isNative = str.contains("native");

        String readLine = null;
        StringBuffer tempBuffer = new StringBuffer();
        StringBuffer messageBuffer = new StringBuffer();

        int maxParameter = 0;
        int numberOfVariable = 0;
        do {
            //read each line upto the end of the method.
            try {
                readLine = br.readLine();
            } catch (IOException e) { e.printStackTrace(); }

            // finding the largest number of parameter.
            maxParameter = this.findParameterNumber(readLine,maxParameter);


            if (readLine.trim().startsWith(".locals")) {
                numberOfVariable = Integer.parseInt( readLine.substring(readLine.lastIndexOf(" ")+1) );
            }

            else if(readLine.trim().equals(".end method")){

                if (isAbstract) {
                    log("Method Type : Abstruct");
                }

                else if (isNative){
                    log("Method Type : Native");
                }

                else
                {
                    int totalVariable = numberOfVariable + maxParameter + 1;

                    if (totalVariable <= 14) {
                        this.setLocals(numberOfVariable + 2);
                        this.setLogMessage(numberOfVariable);

                        messageBuffer.append(this.getLocals());
                        messageBuffer.append(this.getLogMessage());

                    } else {
                        this.setLocals(numberOfVariable);
                        messageBuffer.append(this.getLocals());
                    }

                }
                tempBuffer.append(readLine);

                this.updateFileContent.append(messageBuffer).append(tempBuffer);

                break;
            }
            else
            {
                tempBuffer.append(readLine+"\n");
            }

        }while (true);
    }

    private int findParameterNumber(String str, int maxParameter) {
        String[] a = str.trim().split(" ");
        for (String s : a) {
            String parameter = null;
            boolean isP = false;
            if ((s.startsWith("{p") || s.startsWith("p")) && (s.endsWith("},") || s.endsWith("}"))) {
                parameter = s.substring(s.indexOf("p") + 1, s.indexOf("}"));
                isP = Pattern.matches("[0-9]+", parameter);
            } else if ((s.startsWith("{p") || s.startsWith("p")) && s.endsWith(",")) {
                parameter = s.substring(s.indexOf("p") + 1, s.indexOf(","));
                isP = Pattern.matches("[0-9]+", parameter);

            } else if (s.startsWith("p")) {
                //suppose length of the variable p less than 99. actually we need to dectect is it greater than 16.
                isP = Pattern.matches("[0-9]+", s.substring(s.indexOf("p") + 1));
                if (isP) {
                    parameter = s.substring(s.indexOf("p") + 1);
                }

                //                    String t = s.substring(1);
                //                    if(t.length() <= 2){
                //                        boolean isInteger = Pattern.matches("[0-9]+", t);
                //                        if(isInteger) {
                //                            parameter = s.substring(s.indexOf("p") + 1, s.length());
                //                            isP = true;
                //                        }
                //                    }
            }

            int currentParameter = 0;
            if (isP) {
                logModifyMethod("hello param " + s);
                currentParameter = Integer.parseInt(parameter);
                maxParameter = Math.max(maxParameter,currentParameter);
            }
        }
        return maxParameter;
    }

    @Override
    public String toString() {
        return this.updateFileContent.toString();
    }

    public void writeToFile()
    {
        try {
            FileWriter writer = new FileWriter(this.file);
            writer.write(this.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className.substring(className.lastIndexOf(" ")+2 , className.length()-1 );
//        log(this.className);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName.substring(methodName.indexOf(" ")+1);
//        log(this.methodName);
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(int numberOfVariable) {
        StringConstants stringConstants;
        stringConstants = new StringConstants(numberOfVariable, "Ruhani", this.getClassName()+" : "+this.getMethodName());
        this.logMessage = stringConstants.fullLogMessage;
    }

    public String getLocals() {
        return locals;
    }

    public void setLocals(int numberOfVariable) {
        this.locals = "    .locals " + String.valueOf(numberOfVariable) + "\n";
    }

    public <T> void log(T string)
    {
//        System.out.println(string);
    }

    public <T> void logModifyMethod(T string)
    {
//        System.out.println(string);
    }
}
