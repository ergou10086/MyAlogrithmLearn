#include <iostream>
using namespace std;

bool check(int num) {
    if (num == 0) return true;
    while (num > 0) {
        int digit = num % 10;
        if (digit == 0 || digit == 2 || digit == 4)
            return true;
        num /= 10;
    }
    return false;
}

int main() {
    int n;
    cin >> n;

    int si[n+1], ti[n+1];
    for (int i = 1; i <= n; i++) cin >> si[i];
    for (int i = 1; i <= n; i++) cin >> ti[i];

    int inp = 1, count = 0;
    for (int i = 1; i <= n; i++) {
        int temp = (inp % 2 == 1) ? si[i] : ti[i];
        if (check(temp)) {
            count++;
            inp++;
        }
    }
    cout << count << endl;
    return 0;
}