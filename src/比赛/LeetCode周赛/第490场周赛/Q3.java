package 比赛.LeetCode周赛.第490场周赛;

class Solution3 {
    // 贪心
    // 要让两个二进制字符串异或后更大
    // 高位1尽量多
    public String maximumXor(String s, String t) {
        int n = t.length();

        // t串中1和0的数量
        int count1t = 0, count0t = 0;
        for(char c : t.toCharArray()){
            if(c == '1'){
                count1t++;
            }else{
                count0t++;
            }
        }

        // 重排列t
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);

            // 如果 s 是 0，我们想要 1 来异或出 1
            if(curr == '0'){
                if(count1t > 0){
                    result.append('1');
                    count1t--;
                }else{
                    result.append('0');
                    count0t--;
                }
            // 如果 s 是 1，我们想要 0 来异或出 1
            }else{
                if(count0t > 0){
                    result.append('0');
                    count0t--;
                }else{
                    result.append('1');
                    count1t--;
                }
            }
        }
        StringBuilder xorResult = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int sbit = s.charAt(i) - '0';
            int tbit = result.charAt(i) - '0';
            int xorBit = sbit ^ tbit;
            xorResult.append(xorBit);
        }
        return xorResult.toString();
    }
}

public class Q3 {
}
