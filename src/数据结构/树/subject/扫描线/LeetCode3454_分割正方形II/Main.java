package 数据结构.树.subject.扫描线.LeetCode3454_分割正方形II;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 每个正方形表示为 [x_i, y_i, l_i]，左下角坐标 (x_i, y_i)，边长 l_i
    // 那么右上角是 [x_i + l_i, y_i + l_i]
    public double separateSquares(int[][] squares) {
        List<int[]> sq_c = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        long totalArea = 0;

        for(int[] sq :  squares){
            int x = sq[0], y = sq[1], l = sq[2];
            int top = y + l;

        }

        return 0.0;
    }
}

public class Main {
}
