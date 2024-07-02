package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
        FileWriter fw = new FileWriter("queryAssetCode2.txt");
        String text = br.readLine();
        Pattern pattern = Pattern.compile("\\{\"kindCode(.*?)\"}");
        Matcher matcher = pattern.matcher(text);

        List<String> oddMatches = new ArrayList<>();
        List<String> evenMatches = new ArrayList<>();
        List<String> otherMatches = new ArrayList<>();

        while (matcher.find()) {
            String matchGroup = matcher.group();
            Pattern pattern1 = Pattern.compile(".*\"*物(.*?)物\".*");
            Matcher matcher1 = pattern1.matcher(matchGroup);
            while (matcher1.find()) {
                String match = matcher1.group();
                Pattern pattern2 = Pattern.compile("\"kindCode\":\"([0-9AB][0-9]*|[a-zA-Z]+)\"");
                Matcher matcher2 = pattern2.matcher(matchGroup);
                if (matcher2.find()) {
                    String kindCode = matcher2.group(1);

                    if (kindCode.matches("[0-9]+")) {
                        int kindCodeNum = Integer.parseInt(kindCode);
                        if (kindCodeNum % 2 == 0) {
                            evenMatches.add(match);
                        }
                        else {
                            oddMatches.add(match);
                        }
                    }
                    else if (kindCode.matches("[AB][0-9]+")) {
                        int kindCodeNum = Integer.parseInt(kindCode.substring(1));
                        if (kindCodeNum % 2 == 0) {
                            evenMatches.add(match);
                        }
                        else {
                            oddMatches.add(match);
                        }
                    }
                    else {
                        otherMatches.add(match);
                    }
                }
            }
        }


        List<String> matches = new ArrayList<>();
        matches.addAll(oddMatches);
        matches.addAll(evenMatches);
        matches.addAll(otherMatches);

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
