import heapq

def dijkstra(graph, start, end):
    n = len(graph)
    dist = [float('inf')] * n
    dist[start] = 0
    heap = [(0, start)]

    while heap:
        current_dist, u = heapq.heappop(heap)
        if u == end:
            break
        if current_dist > dist[u]:
            continue
        for v, w in graph[u]:
            if dist[v] > dist[u] + w:
                dist[v] = dist[u] + w
                heapq.heappush(heap, (dist[v], v))

    return dist[end]

def main():
    import sys
    input = sys.stdin.read
    data = input().split()

    idx = 0
    N = int(data[idx])
    M = int(data[idx + 1])
    idx += 2

    graph = [[] for _ in range(N + 1)]

    edges = []
    for _ in range(M):
        A = int(data[idx])
        B = int(data[idx + 1])
        V = int(data[idx + 2])
        graph[A].append((B, V))
        graph[B].append((A, V))
        edges.append((A, B, V))
        idx += 3

    original_dist = dijkstra(graph, N, 1)

    max_dist = -1
    for edge in edges:
        A, B, V = edge
        # Remove the edge
        graph[A].remove((B, V))
        graph[B].remove((A, V))

        current_dist = dijkstra(graph, N, 1)
        if current_dist > max_dist:
            max_dist = current_dist

        # Restore the edge
        graph[A].append((B, V))
        graph[B].append((A, V))

    print(max_dist if max_dist != float('inf') else -1)

if __name__ == "__main__":
    main()