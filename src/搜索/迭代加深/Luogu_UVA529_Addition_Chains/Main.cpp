#include <iostream>
using namespace std;

int n, d;     //d为搜索深度
int a[10005]; //存储加成序列

bool dfs(int u){ //搜索第u层
  if(u==d) return a[u-1]==n;
  for(int i=u-1;i>=0;i--){//cut1:优化搜索顺序
    int t=a[u-1]+a[i];
    if(t>n) continue;     //cut2:越界剪枝
    a[u]=t;
    for(int j=u+1; j<=d; j++) t*=2;
    if(t<n) return false; //cut3:估价未来
    if(dfs(u+1)) return true;
  }
  return false;
}
int main(){
  a[0]=1;
  while(scanf("%d",&n),n){
    d=1;
    while(!dfs(1)) d++; //失败则增加一层
    for(int i=0; i<d; i++) printf("%d ",a[i]);
    puts("");
  }
}