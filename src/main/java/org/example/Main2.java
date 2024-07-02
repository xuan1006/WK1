package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) throws IOException {
        final String regex = "\\{\\\"kindCode(.*?)\\\"\\}";
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(br.readLine());

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }
}
