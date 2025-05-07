n = int(input())
arr = list(map(int, input().split()))

arr.sort()

ans = 1
for j in range(n - 1, -1, -1):
    if arr[j] <= ans:
        print(ans - 1)
        break
    ans += 1
