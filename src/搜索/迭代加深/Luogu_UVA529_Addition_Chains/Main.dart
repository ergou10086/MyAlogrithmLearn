import 'dart:io';

// 定义全局变量 n 为目标值，d 为搜索深度
int n = 0;
int d = 0;
// 数组 a 用于存储加成序列
List<int> a = List.filled(10005, 0);

// 深度优先搜索函数，用于搜索第 u 层
bool dfs(int u) {
  // 当搜索深度达到最大深度 d 时
  if (u == d) {
    // 判断最后一个元素是否等于目标值 n
    return a[u - 1] == n;
  }
  // 优化搜索顺序，从 u - 1 开始往前遍历
  for (int i = u - 1; i >= 0; i--) {
    // 计算新的可能元素
    int t = a[u - 1] + a[i];
    // 越界剪枝，如果新元素大于目标值 n，跳过此次循环
    if (t > n) {
      continue;
    }
    // 将新元素存入数组 a
    a[u] = t;
    // 估价未来，如果未来即使每次都翻倍也无法达到目标值 n，剪枝
    int temp = t;
    for (int j = u + 1; j <= d; j++) {
      temp *= 2;
    }
    if (temp < n) {
      return false;
    }
    // 递归搜索下一层
    if (dfs(u + 1)) {
      return true;
    }
  }
  return false;
}

void main() {
  // 加成序列的第一个元素初始化为 1
  a[0] = 1;
  while (true) {
    // 读取用户输入的目标值 n
    String? input = stdin.readLineSync();
    if (input == null) {
      break;
    }
    n = int.parse(input);
    if (n == 0) {
      break;
    }
    // 初始搜索深度为 1
    d = 1;
    // 如果搜索失败，增加搜索深度
    while (!dfs(1)) {
      d++;
    }
    // 输出加成序列
    for (int i = 0; i < d; i++) {
      stdout.write('${a[i]} ');
    }
    print('');
  }
}