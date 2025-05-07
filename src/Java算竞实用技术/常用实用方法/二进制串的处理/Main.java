package Java算竞实用技术.常用实用方法.二进制串的处理;

public class Main {
    public static void main(String[] args) {

        new Solutions();
    }
}


class Solutions {
    // 二进制字符串与十进制整数的相互转换
    public int binaryStringToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    // 十进制整数转二进制字符串
    public String decimalToBinaryString(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    // 二进制字符串的按位操作
    public String bitwiseAnd(String binary1, String binary2) {
        int num1 = Integer.parseInt(binary1, 2);
        int num2 = Integer.parseInt(binary2, 2);
        int result = num1 & num2;      // 按位与
        int result2 = num1 | num2;     // 按位或
        int result3 = num1 ^ num2;     // 按位异或
        // 格式化输出，取长度较长的那个，并且不足位补0
        // 其中 % 是格式化标志的起始符号，s 表示将参数格式化为字符串，中间为指定最小宽度
        return String.format("%" + Math.max(binary1.length(), binary2.length()) + "s", Integer.toBinaryString(result)).replace(' ', '0');
    }


    // 统计二进制字符串中1的个数
    public int countOnes(String binary) {
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') count++;
        }
        return count;
    }

    // 与1相异或，使特定位翻转

    // 与0相异或，保留原值

    // 反码
    public String InvertedCode(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (sb.substring(i, i + 1).equals("0")) {
                sb.replace(i, i + 1, "1");
            } else {
                sb.replace(i, i + 1, "0");
            }
        }
        return sb.toString();
    }

    // 补码
    public String ComplementCode(String s) {
        String invertedCode = InvertedCode(s);
        StringBuilder sb = new StringBuilder(invertedCode);
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                sb.setCharAt(i, '1');
                break;
            } else {
                sb.setCharAt(i, '0');
            }
        }
        return sb.toString();
    }


    public Solutions() {
        String binary1 = "1010";
        String binary2 = "1100";

        String andResult = bitwiseAnd(binary1, binary2);
        System.out.println("按位与结果: " + andResult);


        String binary = "10101";
        int onesCount = countOnes(binary);
        System.out.println("二进制字符串 " + binary + " 中 1 的个数是: " + onesCount);
    }
}