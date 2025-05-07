def find(u, parent):
    while parent[u] != u:
        parent[u] = parent[parent[u]]
        u = parent[u]
    return u


N, M, L, R = map(int, input().split())

edges = []
for _ in range(M):
    u, v, w = map(int, input().split())
    edges.append((w, u - 1, v - 1))  # 转换为0-based

edges.sort()

parent = list(range(N))
size = [1] * N
ans = 0

for w, u, v in edges:
    root_u = find(u, parent)
    root_v = find(v, parent)
    if root_u != root_v:
        sz1 = size[root_u]
        sz2 = size[root_v]
        ans += (sz1 * sz2) if L <= w <= R else 0
        parent[root_v] = root_u
        size[root_u] += sz2

print(ans)
