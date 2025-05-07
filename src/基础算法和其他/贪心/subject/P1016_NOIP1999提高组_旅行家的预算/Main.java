package 基础算法和其他.贪心.subject.P1016_NOIP1999提高组_旅行家的预算;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions {
    class Pot implements Comparable<Pot> {
        double cost;
        double dist;

        public Pot() {}
        public Pot(double cost, double dist) {
            this.cost = cost;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pot o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    private Pot[] pots = new Pot[100005];

    public Solutions() {
        FastReader sc = new FastReader();
        double d1 = sc.nextDouble();   // 两个城市之间的距离d1
        double c = sc.nextDouble();    // 油箱容量c
        double d2 = sc.nextDouble();  // 每升汽油能行驶的距离 D2
        double p = sc.nextDouble();    // 出发点每升汽油价格P
        int n = sc.nextInt();          // 沿途油站数 N

        pots[0] = new Pot(p, 0);   // 记录起点
        for (int i = 1; i <= n; i++) {
            double dis = sc.nextDouble();
            double cost = sc.nextDouble();
            pots[i] = new Pot(cost, dis);
        }
        pots[n + 1] = new Pot(0, d1);  // 终点

        Arrays.sort(pots, 0, n + 2);

        double canD = c * d2;   // 最大能跑多远
        int totSa = n + 2;      // 一共这些站点

        // 检查相邻站点是否超出最大距离
        for (int i = 0; i < totSa - 1; i++) {
            double distance = pots[i + 1].dist - pots[i].dist;
            if (distance > canD) {
                System.out.println("No Solution");
                return;
            }
        }

        double totalCost = 0.0;
        int current = 0;
        double curOil = 0.0;

        while (current < totSa - 1) {
            double maxDist = pots[current].dist + canD;
            int nextSa = -1;
            boolean foundCheaper = false;
            double minPrice = Double.MAX_VALUE;

            // 寻找可达范围内的加油站
            for (int i = current + 1; i < totSa; i++) {
                if (pots[i].dist > maxDist) break;
                if (pots[i].cost < pots[current].cost) {
                    nextSa = i;
                    foundCheaper = true;
                    break;
                }
                if (pots[i].cost < minPrice) {
                    minPrice = pots[i].cost;
                    nextSa = i;
                }
            }

            if (nextSa == -1) {
                System.out.println("No Solution");
                return;
            }

            double distance = pots[nextSa].dist - pots[current].dist;
            double oilNeeded = distance / d2;

            if (foundCheaper) {
                if (curOil < oilNeeded) {
                    totalCost += (oilNeeded - curOil) * pots[current].cost;
                    curOil = oilNeeded;
                }
                curOil -= oilNeeded;
            } else {
                totalCost += (c - curOil) * pots[current].cost;
                curOil = c - oilNeeded;
            }

            current = nextSa;
        }

        System.out.printf("%.2f", totalCost);
    }

    class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}