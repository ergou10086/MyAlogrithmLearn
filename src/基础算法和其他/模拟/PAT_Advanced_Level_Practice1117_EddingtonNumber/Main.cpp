#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
    int n;
    cin >> n;
    int arr[n];
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    sort(arr, arr + n);

    int ans = 1;
       for (int j = n - 1; j >= 0; --j, ++ans) {
           if (arr[j] <= ans) {
               std::cout << ans - 1 << std::endl;
               break;
           }
       }

    return 0;
}