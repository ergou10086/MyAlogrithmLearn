package 搜索.BFS.template.多源BFS.矩阵距离;

public class Main {
    public static void main(String[] args) {

    }
}

class Solutions{
    private class Node{
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static final int INT = 1010;
    private int n;
    private int[][] graph = new int[INT][INT];   // 存图兼职判重，起点g[0][1] = 1
    private Node[][] path = new Solutions.Node[INT][INT];   // 存路径
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private void dfs(int x, int y){

    }
}
