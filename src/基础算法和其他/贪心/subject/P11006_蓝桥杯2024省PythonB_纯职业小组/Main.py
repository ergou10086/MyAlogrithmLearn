import sys
sys.setrecursionlimit(1000000)
input = lambda:sys.stdin.readline().strip()

T = int(input())
for _ in range(T):
    n, k = map(int, input().split())
    h = {}
    for i in range(n):
        a, b = map(int, input().split())
        if a not in h:
            h[a] = 0
        h[a] += b

    b = []
    for x in h:
        b.append(h[x])

    n = len(b)

    cnt = 0
    res = 0
    for i in range(n):
        cnt += b[i] // 3
        u = min(b[i], 2)
        b[i] -= u
        res += u

    if cnt < k:
        print(-1)
        continue

    c1, c2, c3 = 0, 0, 0
    for x in b:
        c3 += x // 3
        x %= 3
        if x == 1:
            c1 += 1
        elif x == 2:
            c2 += 1

    k -= 1
    res += 1

    v = min(k, c3)
    k -= v
    c3 -= v
    res += v * 3

    v = min(k, c2)
    k -= v
    c2 -= v
    res += v * 2

    v = min(k, c1)
    k -= v
    c1 -= v
    res += v * 1

    print(res)