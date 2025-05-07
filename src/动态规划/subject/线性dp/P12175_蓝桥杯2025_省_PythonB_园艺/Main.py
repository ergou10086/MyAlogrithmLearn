def main():
    import sys
    input = sys.stdin.read

    # 读取输入数据
    data = input().split()
    n = int(data[0])
    tree = list(map(int, data[1:n+1]))

    # 初始化动态规划数组
    # dp[i][d] 表示以第i棵树结尾，间隔为d的最长子序列长度
    dp = [[0] * n for _ in range(n)]
    max_len = 1  # 至少可以选一棵树

    # 初始化：每棵树可以单独形成一个子序列
    for i in range(n):
        dp[i][0] = 1

    # 动态规划主过程
    for i in range(n):  # 遍历每棵树作为结尾
        for j in range(i):  # 检查前面的所有树
            if tree[j] < tree[i]:  # 只有当前树比前面的树高时才考虑
                diff = i - j  # 计算间隔

                # 获取以树j结尾、间隔为diff的最长子序列长度
                prev = dp[j][diff]

                # 如果prev为0，说明树j是单独存在的
                # 此时可以形成一个长度为2的子序列（树j和树i）
                if prev == 0:
                    prev = 1

                # 如果发现更长的子序列，就更新
                if dp[i][diff] < prev + 1:
                    dp[i][diff] = prev + 1

                    # 更新全局最大值
                    if dp[i][diff] > max_len:
                        max_len = dp[i][diff]

    # 输出结果
    print(max_len)

if __name__ == "__main__":
    main()