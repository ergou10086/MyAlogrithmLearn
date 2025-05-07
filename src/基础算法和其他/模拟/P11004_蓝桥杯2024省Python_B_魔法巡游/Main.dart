import 'dart:io';

bool check(int num) {
  if (num == 0) return true;
  int n = num;
  while (n > 0) {
    int digit = n % 10;
    if (digit == 0 || digit == 2 || digit == 4) return true;
    n ~/= 10;
  }
  return false;
}

void main() {
  List<String> input = stdin.readLineSync()!.split(' ');
  int ptr = 0;
  int n = int.parse(input[ptr++]);

  List<int> si = List.filled(n + 1, 0);
  List<int> ti = List.filled(n + 1, 0);

  for (int i = 1; i <= n; i++) {
    si[i] = int.parse(input[ptr++]);
  }
  for (int i = 1; i <= n; i++) {
    ti[i] = int.parse(input[ptr++]);
  }

  int inp = 1, count = 0;
  for (int i = 1; i <= n; i++) {
    int temp = (inp % 2 == 1) ? si[i] : ti[i];
    if (check(temp)) {
      count++;
      inp++;
    }
  }
  print(count);
}