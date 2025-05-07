import sys
from typing import List

class FastReader:
    def __init__(self):
        self.input = sys.stdin.read().split()
        self.pointer = 0

    def next(self) -> str:
        if self.pointer >= len(self.input):
            return ""
        result = self.input[self.pointer]
        self.pointer += 1
        return result

    def next_int(self) -> int:
        return int(self.next())

class Solutions:
    def __init__(self):
        self.sc = FastReader()
        self.n = self.sc.next_int()  # 希望购买物品的个数
        self.m = self.sc.next_int()  # 总钱数

        self.w = [0] * 105  # 物品价格
        self.v = [0] * 105  # 物品重要度
        self.minn = float('inf')  # 最小价格

        # 读取输入并找到最小价格
        for i in range(1, self.n + 1):
            self.w[i] = self.sc.next_int()
            self.v[i] = self.sc.next_int()
            self.minn = min(self.minn, self.w[i])

        # 分别存储价格与最小价格差为 0, 1, 2, 3 的物品重要度
        self.w0 = []
        self.w1 = []
        self.w2 = []
        self.w3 = []

        # 将物品按价格与最小价格的差值分类
        for i in range(1, self.n + 1):
            diff = self.w[i] - self.minn
            if diff == 0:
                self.w0.append(self.v[i])
            elif diff == 1:
                self.w1.append(self.v[i])
            elif diff == 2:
                self.w2.append(self.v[i])
            elif diff == 3:
                self.w3.append(self.v[i])

        # 贪心，将每种物品按重要度从高到低排序
        self.w0.sort(reverse=True)
        self.w1.sort(reverse=True)
        self.w2.sort(reverse=True)
        self.w3.sort(reverse=True)

        # 计算每种物品的前缀和
        self.s0 = self.calculate_prefix_sum(self.w0)
        self.s1 = self.calculate_prefix_sum(self.w1)
        self.s2 = self.calculate_prefix_sum(self.w2)
        self.s3 = self.calculate_prefix_sum(self.w3)

        # 枚举每种物品的数量
        self.ans = 0
        for i in range(len(self.w0) + 1):
            for j in range(len(self.w1) + 1):
                for k in range(len(self.w2) + 1):
                    total_cost = i * self.minn + j * (self.minn + 1) + k * (self.minn + 2)
                    if total_cost <= self.m:
                        p = (self.m - total_cost) // (self.minn + 3)
                        p = min(p, len(self.w3))
                        # 选前几个物品
                        current_sum = self.s0[i] + self.s1[j] + self.s2[k] + self.s3[p]
                        self.ans = max(self.ans, current_sum)

        print(self.ans)

    def calculate_prefix_sum(self, arr: List[int]) -> List[int]:
        prefix_sum = [0]
        for num in arr:
            prefix_sum.append(prefix_sum[-1] + num)
        return prefix_sum

if __name__ == "__main__":
    Solutions()