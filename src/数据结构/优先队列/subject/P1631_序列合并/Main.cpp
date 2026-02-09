//luogu P1631 序列合并
#include <iostream>
#include <cstdio>
#include <queue>
using namespace std;

// 思路是让A中的每个数与B中的一个数相加，生成n个有序队列
// 使用小根堆维护最小值

const int N=100005;
int a[N],b[N],id[N];
// id[i],用来记录该a数字用于加了b数组里面的第几个数

priority_queue< pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > q;


int main(){
    int n; scanf("%d",&n);
    for(int i = 1; i <= n; i++) scanf("%d",&a[i]);

    for(int i = 1; i <= n; i++){
        scanf("%d", &b[i]);
        id[i] = 1;
        q.push({a[1] + b[i], i});
    }

    while(n--){
        printf("%d ", q.top().first);
        int i = q.top().second;
        q.pop();
        q.push({a[++id[i]] + b[i], i});
    }

    return 0;
}