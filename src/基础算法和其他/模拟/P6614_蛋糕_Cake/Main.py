import math

class Node:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __lt__(self, cmp):
        if self.x != cmp.x:
            return self.x < cmp.x
        else:
            return self.y > cmp.y

n, a, b = map(int, input().split())
Cake = [None]  # 使用1-based索引

for _ in range(n):
    x, y = map(int, input().split())
    Cake.append(Node(x, y))

Cake[1:].sort()  # 对1到n的元素排序

K = 10 ** 12
index = n * a // (a + b)
print(K, Cake[index].x, Cake[index].y)