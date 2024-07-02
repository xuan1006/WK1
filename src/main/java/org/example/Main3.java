package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
        FileWriter fw = new FileWriter("queryAssetCode.txt");
        String text = br.readLine();
        Pattern pattern = Pattern.compile("\\{\\\"kindCode(.*?)\\\"\\}");
        Matcher matcher = pattern.matcher(text);

        List<String> matches = new ArrayList<>();

        while (matcher.find()){
            Pattern pattern1 = Pattern.compile(".*\\\"*物(.*?)物\\\".*");
            Matcher matcher1 = pattern1.matcher(matcher.group());
            while (matcher1.find()){
                matches.add(matcher1.group());
            }
        }
        for (String match : matches) {
            fw.write(match + "\n");
        }
        fw.close();
        br.close();

        for (String match : matches) {
            System.out.println(match);
        }
    }
}
