package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
        FileWriter fw = new FileWriter("queryAssetCode.txt");
        String text = br.readLine();
        Pattern pattern = Pattern.compile("\\{\\\"kindCode(.*?)\\\"\\}");
        Matcher matcher = pattern.matcher(text);

        List<String> odd = new ArrayList<>();
        List<String> even = new ArrayList<>();
        List<String> other = new ArrayList<>();

        while (matcher.find()) {
            String matchGroup = matcher.group();
            Pattern pattern1 = Pattern.compile(".*\\\"*物(.*?)物\\\".*");
            Matcher matcher1 = pattern1.matcher(matchGroup);
            while (matcher1.find()) {
                String match = matcher1.group();
                Pattern pattern2 = Pattern.compile("\\\"kindCode\\\":\\\"(\\d+|[a-zA-Z]+)\\\"");
                Matcher matcher2 = pattern2.matcher(matchGroup);
                if (matcher2.find()) {
                    String kindCode = matcher2.group(1);

                    if (kindCode.matches("\\d+")) {
                        int kindCodeNum = Integer.parseInt(kindCode);
                        if (kindCodeNum % 2 == 0) {
                            even.add(match);
                        }
                        else {
                            odd.add(match);
                        }
                    }
                    else {
                        other.add(match);
                    }
                }
            }
        }
        List<String> matches = new ArrayList<>();
        matches.addAll(odd);
        matches.addAll(even);
        matches.addAll(other);


        for (String match : matches) {
            fw.write(match + "\n");
        }

        for (String match : matches) {
            System.out.println(match);
        }

        fw.close();
        br.close();


    }
}

