package 基础算法和其他.模拟.P12170_蓝桥杯_2025_省_Python_B_攻击次数;

public class Main {
    public static void main(String[] args) {
        // 能一起上就一起上，不能一起上，就上攻击力高的
        int hp = 2025;
        int cnt = 0;

        while(hp > 0){
            cnt++;
            int hero1 = 5;   // 英雄 1 的固定伤害
            int hero2 = (cnt % 2 == 1) ? 15 : 2;   // 英雄 2 的回合机制伤害
            int hero3 = 0;
            if(cnt % 3 == 1){
                hero3 = 2;
            }else if(cnt % 3 == 2){
                hero3 = 10;
            }else if(cnt % 3 == 0){
                hero3 = 7;
            }

            hp -= hero1 + hero2 + hero3;
        }
        System.out.println(cnt);
    }
}
