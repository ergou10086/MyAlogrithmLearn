import 'dart:io';

void main() {
  const int MAXN = 40001;
  // 存储每个石头的权值
  List<int> c = List.filled(MAXN, 0);
  // 用于标识每个石头能否跳到的 BitSet 数组
  List<List<bool>> bs = List.generate(MAXN, (_) => List.filled(MAXN, false));

  // 读取石头的数量
  int n = int.parse(stdin.readLineSync()!);

  // 读取每个石头的权值
  List<String> inputValues = stdin.readLineSync()!.split(' ');
  for (int i = 1; i <= n; i++) {
    c[i] = int.parse(inputValues[i - 1]);
  }

  int ans = 0;
  // 从后往前遍历每个位置
  for (int i = n; i >= 1; i--) {
    // 标记当前石头的状态
    bs[i][c[i]] = true;

    // 跳向第 i + c(i) 块石头，合并状态
    if (i + c[i] <= n) {
      for (int j = 0; j < MAXN; j++) {
        bs[i][j] = bs[i][j] || bs[i + c[i]][j];
      }
    }

    // 跳向第 2 * i 块石头，合并状态
    if (2 * i <= n) {
      for (int j = 0; j < MAXN; j++) {
        bs[i][j] = bs[i][j] || bs[2 * i][j];
      }
    }

    // 统计 bs[i] 中为 true 的数量
    int count = 0;
    for (int j = 0; j < MAXN; j++) {
      if (bs[i][j]) {
        count++;
      }
    }
    // 更新最大能跳到的石头数量
    ans = ans > count ? ans : count;
  }

  // 输出结果
  print(ans);
}