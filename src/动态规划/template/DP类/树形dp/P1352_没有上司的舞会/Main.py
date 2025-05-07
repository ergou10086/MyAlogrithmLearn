import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

def dfs(u):
    f[u][1] = w[u]  # 选u的快乐指数,赋初值
    for v in a[u]:
        dfs(v)
        f[u][0] += max(f[v][0], f[v][1])
        f[u][1] += f[v][0]


n = int(input())
w = [0] * 6010
a = [[] for i in range(n + 5)]  # 邻接表存图
fa = [0] * 6009
f = [[0] * 2 for _ in range(6008)]
for i in range(1, n + 1):
    w[i] = int(input())
for i in range(n - 1):
    x, y = map(int, input().split())
    a[y].append(x)
    fa[x] = 1  # 有父节点

root = 1
while fa[root]:
    root += 1
dfs(root)
print(max(f[root][0], f[root][1]))