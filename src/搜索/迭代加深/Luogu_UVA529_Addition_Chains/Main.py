# 全局变量 n 表示目标值，d 表示搜索深度
n = 0
d = 0
# 列表 a 用于存储加成序列
a = [0] * 10005

# 深度优先搜索函数，搜索第 u 层
def dfs(u):
    # 当搜索深度达到最大深度 d 时
    if u == d:
        # 判断最后一个元素是否等于目标值 n
        return a[u - 1] == n
    # 优化搜索顺序，从 u - 1 开始往前遍历
    for i in range(u - 1, -1, -1):
        # 计算新的可能元素
        t = a[u - 1] + a[i]
        # 越界剪枝，如果新元素大于目标值 n，跳过此次循环
        if t > n:
            continue
        # 将新元素存入列表 a
        a[u] = t
        # 估价未来，如果未来即使每次都翻倍也无法达到目标值 n，剪枝
        temp = t
        for j in range(u + 1, d + 1):
            temp *= 2
        if temp < n:
            return False
        # 递归搜索下一层
        if dfs(u + 1):
            return True
    return False


while True:
    try:
        # 读取用户输入的目标值 n
        n = int(input())
        if n == 0:
            break
        # 加成序列的第一个元素初始化为 1
        a[0] = 1
        # 初始搜索深度为 1
        d = 1
        # 如果搜索失败，增加搜索深度
        while not dfs(1):
            d += 1
        # 输出加成序列
        print(' '.join(map(str, a[:d])))
    except EOFError:
        break