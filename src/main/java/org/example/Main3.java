package org.example;

import java.io.*;
import java.util.regex.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
        FileWriter fw = new FileWriter("queryAssetCode.txt");
        String text = br.readLine();
        Pattern pattern = Pattern.compile("\\{\\\"kindCode(.*?)\\\"\\}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(text.substring(start,end));
            fw.write(text.substring(start,end)+"\n");
        }
        fw.close();
    }
}
