#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

const int N = 100005;
int n, a[N];
int q[N], siz[N], cnt, ans = 1e9;
//q[k]第k组的最大实力值
//siz[k]第k组的人数
//cnt记录组数

int main(){
    cin >> n;
    for(int i=1;i<=n;i++) cin >> a[i];

    sort(a + 1, a + n + 1);

    q[0] = 1e9;   // 保证k为0时候能新开一组
    for(int i = 1; i <= n; i++){
        int k;
        // -q是为了得到对应的数组索引 k
        k = upper_bound(q + 1, q + cnt + 1, a[i] - 1) - q - 1;
        if(q[k] == a[i] - 1)  q[k] = a[i], siz[k]++;   // 接入第k组
        else q[++cnt] = a[i], siz[cnt] = 1;   // 新开一组
    }

    for(int i = 1; i <= cnt; i++){
        ans = min(ans, siz[i]);
    }

    cout << ans << endl;

    return 0;
}