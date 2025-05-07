import 'dart:io';

void main() {
  int n = int.parse(stdin.readLineSync()!);
  for (int i = 0; i < n; i++) {
    List<String> input = stdin.readLineSync()!.split(' ');
    int year = int.parse(input[0]);
    int month = int.parse(input[1]);
    int day = int.parse(input[2]);

    if ((month == 9 && day == 30) || (month == 11 && day == 30) || ((month + day) % 2 == 0)) {
      print("YES");
    } else {
      print("NO");
    }
  }
}