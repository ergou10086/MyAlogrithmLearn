package 基础算法和其他.模拟.P10899_蓝桥杯2024省C_劲舞团;

import java.sql.Time;
import java.util.Scanner;

public class Main {
	// 重复读入两个字符判断是否相等即可。
	// 同时记录上次时间，与本次比较。如果不满足连击的条件就把 K 清零
	// 毫秒看是否不超过1000
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char n, m;
		long ntime, ftime = 0;
		int res = 0, ans = 0;
		
		while(scanner.hasNext()) {
			 n = scanner.next().charAt(0);
	         m = scanner.next().charAt(0);
	         ntime = scanner.nextLong();
	         if (n == m && ntime - ftime <= 1000) {
	        	 res++;
	         }else {
	        	 if(res > ans) {
	        		 ans = res;
	        	 }
	        	 res = 0;
	         }
	         ftime = ntime;
		}
		System.out.println(ans);
		scanner.close();
	}
}


