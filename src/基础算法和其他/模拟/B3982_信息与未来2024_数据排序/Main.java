package 基础算法和其他.模拟.B3982_信息与未来2024_数据排序;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solutions();
    }
}

/*
4
Name,p1,p2,p3,Score
ZhangSan,40,30,28,98
LiSi,40,28,30,98
WangWu,40,25,20,85
3
Score-
Name+
p3-

Name,p1,p2,p3,Score
LiSi,40,28,30,98
ZhangSan,40,30,28,98
WangWu,40,25,20,85
 */

class Solutions{
    class SortCondition {
        String name;   // 列名
        char order;   // 顺序

        SortCondition(){}
        SortCondition(String name, char order) {
            this.name = name;
            this.order = order;
        }
    }

    private int reverseStringCompare(String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLength; i++) {
            int result = Character.compare(s2.charAt(i), s1.charAt(i));
            if (result != 0) {
                return result;
            }
        }
        return Integer.compare(s2.length(), s1.length());
    }

    public Solutions() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out), true);

        // 行数
        int n = Integer.parseInt(br.readLine());

        // 读取表头并构建列索引映射
        String[] header = br.readLine().split(",");  // 读表头
        Map<String, Integer> colIndex = new HashMap<>();  // 存储列名和对应的列索引
        for (int i = 0; i < header.length; i++){
            colIndex.put(header[i], i);
        }

        // 存数据行
        List<String[]> dataRows = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            dataRows.add(br.readLine().split(","));
        }

        // 判断这一列是什么数据类型
        Map<String, String> colTypes = new HashMap<>();  // 纯数据类型
        if(!dataRows.isEmpty()){
            String[] firstRow = dataRows.get(0);   // 读取表头
            for(String name: header){
                int idx = colIndex.get(name);   // 根据列名读索引
                String cell = firstRow[idx];    // 得到该格的数据
                // 判断每列的数据类型是数值型（num）还是字符串型（str）。
                if (cell.matches("\\d+")) {   // 正则表达式匹配一个或多个连续的数字字符
                    colTypes.put(name, "num");
                } else {
                    colTypes.put(name, "str");
                }
            }
        }

        // 排序条件数
        int m = Integer.parseInt(br.readLine());
        List<SortCondition> sortConditions = new ArrayList<>();    // 存排序方式
        for(int i = 0; i < m; i++){
            String s = br.readLine();
            sortConditions.add(new SortCondition(
                    s.substring(0, s.length() - 1),
                    s.charAt(s.length() - 1)
            ));
        }

        // 自定义排序
        dataRows.sort((row1, row2) -> {
            for(SortCondition condition: sortConditions){
                String name = condition.name;   // 要排序的列名
                char order = condition.order;   // 排序方式
                int idx = colIndex.get(name);   // 列号
                String cellValue1 = row1[idx];
                String cellValue2 = row2[idx];
                String colType = colTypes.get(name);   // 数据类型
                int result;

                // 数字
                if ("num".equals(colType)) {
                    int num1 = Integer.parseInt(cellValue1);
                    int num2 = Integer.parseInt(cellValue2);
                    result = order == '-' ? Integer.compare(num2, num1) : Integer.compare(num1, num2);
                // 字符串
                }else{
                    // 逆序，字典逆序
                    if (order == '-') {
                        result = reverseStringCompare(cellValue1, cellValue2);
                    }else{
                        // 正序直接调用compareTo排序
                        result = cellValue1.compareTo(cellValue2);
                    }
                }
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        });

        pw.println(String.join(",", header));
        for(String[] row: dataRows){
            pw.println(String.join(",", row));
        }
    }
}
