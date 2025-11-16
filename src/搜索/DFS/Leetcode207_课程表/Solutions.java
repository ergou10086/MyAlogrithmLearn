package 搜索.DFS.Leetcode207_课程表;

import java.util.*;
/**
 * 一共有 n 门课要上，编号为 0 ~ n-1。
 * 先决条件 [p, x]，意思是必须先上课 p，才能上课 x
 * 给你 n、和一个先决条件表，请你判断能否完成所有课程。
 * 图的深搜
 */
public class Solutions {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // prerequisites[i] = [ai, bi] 表示要学ai必须先学bi
        // 所以边的方向是 bi -> ai
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        // 状态数组 0-未访问，1-访问中，2-已完成
        int[] visited = new int[numCourses];

        // 对每个课进行DFS
        for(int i = 0; i < numCourses; i++){
            // 没走过
            if(visited[i] == 0){
                if (hasCycle(graph, visited, i)) {
                    return false;  // 发现环，无法完成课程
                }
            }
        }

        return true;  // 没有环，可以完成所有课程
    }

    /**
     * DFS检测是否存在环
     * @param graph 邻接表
     * @param visited 访问状态数组
     * @param course 当前课程
     * @return true表示存在环
     */
    private boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        // 访问这个节点后发现是自己，那也是环
        if(visited[course] == 1) return true;

        // 访问完成，肯定脱离了环
        if(visited[course] == 2) return false;

        // 标记该点为访问中
        visited[course] = 1;

        // 遍历当前依赖的所有课程
        for(int next : graph.get(course)){
            if(hasCycle(graph, visited, next)) return true;
        }

        // 完成
        visited[course] = 2;

        return false;
    }
}
