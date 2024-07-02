package org.example;

import java.io.*;
import java.util.regex.*;

public class Main1 {
    public static void main(String[] args) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // 将内容作为一个字符串获取
            String jsonString = sb.toString();

            // 根据分隔符 "{}" 将内容分割成单个记录
            String[] records = jsonString.split("\\{");

            // 编译正则表达式以匹配 '物' 后面跟任意字符再跟 '物'
            Pattern pattern = Pattern.compile(".*物.*物.*");

            // 将过滤后的数据写入一个新的文本文件
            FileWriter fw = new FileWriter("queryAssetCode.txt");

            // 处理每个记录
            for (String record : records) {
                // 调整分割记录，使其成为有效的JSON对象，通过添加花括号
                if (!record.startsWith("{")) {
                    record = "{" + record;
                }
                if (!record.endsWith("}")) {
                    record = record + "}";
                }

                // 在记录中找到 "commonName" 键
                Matcher commonNameMatcher = Pattern.compile("\"commonName\"\\s*:\\s*\"(.*?)\"").matcher(record);
                if (commonNameMatcher.find()) {
                    String commonName = commonNameMatcher.group(1);
                    // 检查 commonName 是否匹配模式
                    Matcher matcher = pattern.matcher(commonName);
                    if (matcher.find()) {
                        fw.write(record + "\n");
                        System.out.println(record);
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
