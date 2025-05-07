import 'dart:io';

void main() {
  int n = int.parse(stdin.readLineSync()!);
  List<int> arr = stdin.readLineSync()!.split(' ').map(int.parse).toList();

  arr.sort();

  int ans = 1;
  for (int j = arr.length - 1; j >= 0; j--, ans++) {
    if (arr[j] <= ans) {
      print(ans - 1);
      break;
    }
  }
}