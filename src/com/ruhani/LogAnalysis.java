package com.ruhani;

import java.io.*;
import java.util.*;

public class LogAnalysis {
    public File fileName;
    private HashMap<String, Integer> map;
    private HashMap<String, ArrayList<String>> methodMap;

    public LogAnalysis(String fileName) {
        this.fileName = new File(fileName);
        this.map = new HashMap<>();
        this.methodMap = new HashMap<>();
        this.readFile();
    }

    private void readFile()
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            ArrayList<String>stringArrayList = new ArrayList<>();
//            HashMap<String, Integer> map = new HashMap<>();
//            HashMap<String, ArrayList<String>> methodMap = new HashMap<>();
            String readLine;

            while ( (readLine = bufferedReader.readLine()) != null)
            {
                String[] strings = readLine.split(" : ");
                if (strings.length > 1)
                {
                    ArrayList<String> methodList = new ArrayList<>();
                    if (this.map.containsKey(strings[1].trim()) ) {
                        this.map.put( strings[1].trim(),this.map.get(strings[1].trim()) + 1);

                        //get called method from each class
                        methodList = this.methodMap.get(strings[1].trim());
                        if(!methodList.contains(strings[2].trim())){
                            methodList.add(strings[2].trim());
                            this.methodMap.put( strings[1].trim(), methodList);
                        }
                    } else {
                        this.map.put(strings[1].trim(),1);

                        //get called method from each class
                        methodList.add(strings[2].trim());
                        this.methodMap.put( strings[1].trim(), methodList);
                    }
                }
            }
//            this.sortByKey(map);

            this.sortByValue(this.map);
//            Comparator<String> comparator = new Comparator<String>() {
//                @Override
//                public int compare(String s1, String s2) {
//                    return s1.compareTo(s2);
//                }
//            };
            Collections.sort(stringArrayList,new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });

//            for (String string : stringArrayList)
            {
//                string = "F:\\test-imo\\apk-src\\smali\\" + string.replace("/","\\") + ".smali";
//                log(string);
            }
            log("Total Called Class : "+map.size());
//            log(stringArrayList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortByKey(Map<String, Integer> unsortedMap)
    {
        Map<String, Integer> sortedMap = new TreeMap<>(unsortedMap);
        Iterator<String> sortedKeyIterator = sortedMap.keySet().iterator();
        while(sortedKeyIterator.hasNext()) {
            String key = sortedKeyIterator.next();
            String string = "F:\\test-imo\\apk-src\\smali\\" + key.replace("/","\\") + ".smali";
            log(sortedMap.get(key) + "\t\t" + string);
        }
    }

    private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            String string = entry.getKey();
            string = "F:\\test-imo\\apk-src\\smali\\" + string.replace("/","\\") + ".smali";
//            if(!string.contains("F:\\test-imo\\apk-src\\smali\\com\\imo\\android\\imoim"))
//                if(!string.contains("F:\\test-imo\\apk-src\\smali\\android\\support\\"))
                {
                    log(entry.getValue() + "\t\t" + string);
                    ArrayList<String> methodList = this.methodMap.get(entry.getKey());
                    for (String str : methodList)
                    {
                        log("\t\t\t\t\t" + str);
                    }
                }

            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    public HashMap<String, Integer> getMap() {
        return (HashMap<String, Integer>) map.clone();
    }

    public HashMap<String, ArrayList<String>> getMethodMap() {
        return (HashMap<String, ArrayList<String>>) methodMap.clone();
    }

    public <T> void log (T data)
    {
        System.out.println(data);
    }

}
