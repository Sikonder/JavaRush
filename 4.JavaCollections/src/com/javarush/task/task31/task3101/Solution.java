package com.javarush.task.task31.task3101;

import java.io.*;

import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static TreeSet<File> Lower50 = new TreeSet<>();
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath,allFilesContent);
        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)){
            deepSearch(path);
            TreeMap<String,File> fileTreeMap = new TreeMap<>();
            for(File f:Lower50){
                fileTreeMap.put(f.getName(),f);
            }
            for(Map.Entry<String, File> pair : fileTreeMap.entrySet()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pair.getValue()));
                String s = "";
                while ((s = bufferedReader.readLine())!=null)
                    fileOutputStream.write((s+"\n").getBytes());
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    public static void deepSearch(File f){
        if(f.isDirectory()){
            for(File ff : f.listFiles()){
                deepSearch(ff);
            }
        }else if(f.isFile()){
            if(f.length() > 50)
                FileUtils.deleteFile(f);
            else
                Lower50.add(f);
        }
    }
}
