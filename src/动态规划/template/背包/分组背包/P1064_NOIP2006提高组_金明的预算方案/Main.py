n, m = map(int, input().split())
# qi=0，表示该物品本身是主件
miv = [0] * (m + 1)  # 主件价格
mip = [0] * (m + 1)  # 主件价格*重要度
aiq = [[0] * 3 for _ in range(m+1)]  # 副件价格
aiv = [[0] * 3 for _ in range(m+1)]  # 附件价格*重要度

for i in range(1,m+1):
    v, p, q = map(int, input().split())
    if q == 0:
        miv[i] = v
        mip[i] = v * p
    else:
        aiq[q][0] += 1
        aiq[q][aiq[q][0]] = v
        aiv[q][aiq[q][0]] = v * p

# 1.不选，然后去考虑下一个          dp[i] = dp[i+1]
# 2.选且只选这个主件               dp[i] = max( dp[i], dp[i - miv[p]] + mip[p] )
# 3.选这个主件，并且选附件1         dp[i] = max( dp[i], dp[i - miv[p] - aiq[p][1]] + mip[p] + aiv[p][1] )
# 4.选这个主件，并且选附件2         dp[i] = max( dp[i], dp[i - miv[p] - aiq[p][2]] + mip[p] + aiv[p][2] )
# 5.选这个主件，并且选附件1和附件2.  dp[i] = max( dp[i], dp[i - miv[p] - aiq[p][1] - aiq[p][2]] + mip[p] + aiv[p][1] + aiv[p][2] )

dp = [0] * 35000
for p in range(1,m+1):
    for i in range(n,0,-1):
        # 先把主件选上
        if miv[p] != 0 and i >= miv[p]:
            dp[i] = max(dp[i], dp[i - miv[p]] + mip[p])

            # 选这个主件，并且选附件1，前提是钱够
            if i >= miv[p] + aiq[p][1]:
                dp[i] = max(dp[i], dp[i - miv[p] - aiq[p][1]] + mip[p] + aiv[p][1])

            # 选这个主件，并且选附件2，前提是钱够
            if i >= miv[p] + aiq[p][2]:
                dp[i] = max(dp[i], dp[i - miv[p] - aiq[p][2]] + mip[p] + aiv[p][2])

            # 选这个主件，并且选附件1,2，前提是钱够
            if i >= miv[p] + aiq[p][2] + aiq[p][1]:
                dp[i] = max(dp[i], dp[i - miv[p] - aiq[p][1] - aiq[p][2]] + mip[p] + aiv[p][1] + aiv[p][2])

print(dp[n])