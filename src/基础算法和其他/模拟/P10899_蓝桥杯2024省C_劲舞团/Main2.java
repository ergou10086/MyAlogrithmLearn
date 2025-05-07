package 基础算法和其他.模拟.P10899_蓝桥杯2024省C_劲舞团;

// 使用文件读取，IO流编写

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Record{
    // 连续的两次敲击间间隔时间都小于等于 1s1s
    char corKey;    // 正确的敲击字符
    char reaKey;   // 实际打出的字符
    long time;   // 打出字符的时间对应的毫秒时间戳

    public Record(char corKey, char reaKey, long time){
        this.corKey = corKey;
        this.reaKey = reaKey;
        this.time = time;
    }
}

public class Main2 {
    public static void main(String[] args) {
        List<Record> records = readFile("D:\\WorkSpace\\JavaDemo\\IDEA\\Algorithm\\src\\基础算法和其他\\模拟\\P10899_蓝桥杯2024省C_劲舞团\\log.txt");
        int maxCombo = calcMaxCombo(records);
        System.out.println(maxCombo);
    }

    private static List<Record> readFile(String filename) {
        List<Record> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(" ");
                char corKey = parts[0].charAt(0);
                char reaKey = parts[1].charAt(0);
                long time = Long.parseLong(parts[2]);
                records.add(new Record(corKey, reaKey, time));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    private static int calcMaxCombo(List<Record> records){
        int combo = 0;
        int currCombo = 0;
        long lastTime = 0;

        for(int i = 0; i < records.size(); i++){
            Record record = records.get(i);
            if(record.corKey == record.reaKey){
                if (i == 0 || record.time - lastTime <= 1000) {
                    currCombo++;
                }else{
                    currCombo = 1;
                }
                combo = Math.max(combo, currCombo);
            }else{
                currCombo = 0;
            }
            lastTime = record.time;
        }

        return combo;
    }
}
