import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

def dfs(u):
    f[u][1] = w[u]
    for v in e[u]:
        dfs(v)
        for j in range(m+1,0,-1):
            for k in range(j):
                f[u][j] = max(f[u][j], f[u][j - k] + f[v][k])

n, m = map(int, input().split())
w = [0] * 305
e = [[] for _ in range(n + 1)]
f = [[0] * 305 for _ in range(305)]
for i in range(1, n+1):
    k, p = map(int, input().split())
    w[i] = p
    e[k].append(i)
dfs(0)
print(f[0][m+1])
