package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipFileName = args[1];
        File file = new File(fileName);

        Map<String,ByteArrayOutputStream> arcFiles = new HashMap<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName))){
            ZipEntry entry;
            while ((entry=zipInputStream.getNextEntry())!=null){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipInputStream.read(buffer))!=-1){
                    byteArrayOutputStream.write(buffer,0,count);
                }
                arcFiles.put(entry.getName(),byteArrayOutputStream);
            }
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (Map.Entry<String,ByteArrayOutputStream> pair : arcFiles.entrySet()){
                if(pair.getKey().substring(pair.getKey().lastIndexOf("/")+1).equals(file.getName())) continue;
                zipOutputStream.putNextEntry(new ZipEntry(pair.getKey()));
                zipOutputStream.write(pair.getValue().toByteArray());
            }
            ZipEntry zipEntry = new ZipEntry("new/" + file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            Files.copy(file.toPath(),zipOutputStream);

        }
    }
}
