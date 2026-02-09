package 搜索.BFS.subject.LeetCode207_课程表;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 拓扑排序？一眼 Kahn 了
class Solution {
    // 这个有向图是否存在环？
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 邻接表存有向图
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;  // 入度加一
        }

        // 入度为0入队列，开始从这学习
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int pass = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            pass++;

            // 开始走
            for (int next : graph.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return pass == numCourses;
    }
}

public class Main {
}
