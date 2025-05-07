import heapq
import sys
input = sys.stdin.readline

n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()
b.sort()
judge = [0] * (n+1)

result = []
min_sums = []
heapq.heapify(min_sums)
heapq.heappush(min_sums,(a[0]+b[0],0,0))  # 将元组改为包含三个元素

for _ in range(n):
    current_sum, i, j = heapq.heappop(min_sums)
    result.append(current_sum)

    if i + 1 < n:
        heapq.heappush(min_sums,(a[i+1]+b[j], i+1, j))  # 修改元组的构造方式
    if j + 1 < n and (i, j + 1) not in min_sums:
        heapq.heappush(min_sums,(a[i]+b[j+1], i, j+1))  # 修改元组的构造方式

print(" ".join(map(str,result)))
