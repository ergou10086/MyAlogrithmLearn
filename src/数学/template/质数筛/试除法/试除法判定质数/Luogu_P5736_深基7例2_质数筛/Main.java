package 数学.template.质数筛.试除法.试除法判定质数.Luogu_P5736_深基7例2_质数筛;

import java.util.Scanner;

/*
for(int i = 2; i * i <= x; i++){
    if(x % i == 0) return false;
}
 */

public class Main {
    public static void main(String[] args) {
        new Solutions();
    }
}

class Solutions{
    private boolean is_prim(int x){
        if(x == 1) return false;
        for(int i = 2; i * i <= x; i++){
            if(x % i == 0) return false;
        }
        return true;
    }

    public Solutions(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            if(is_prim(x)) System.out.println(x + " ");
        }
    }

}
