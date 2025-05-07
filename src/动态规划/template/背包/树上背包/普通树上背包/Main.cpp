#include<iostream>
#include<cstring>
using namespace std;

const int N=110;
int n,V,p,root;
int v[N],w[N];
int h[N],to[N],ne[N],tot; //邻接表
int f[N][N];

void add(int a,int b){
  to[++tot]=b;ne[tot]=h[a];h[a]=tot;
}
void dfs(int u){
  for(int i=v[u];i<=V;i++) f[u][i]=w[u];
  for(int i=h[u]; i; i=ne[i]){  //子节点
    int s=to[i];
    dfs(s);
    for(int j=V;j>=v[u];j--)    //体积
      for(int k=0;k<=j-v[u];k++)//决策
        f[u][j]=max(f[u][j],f[u][j-k]+f[s][k]);
  }
}
int main(){
  cin>>n>>V;              //物品个数,背包容量
  for(int i=1;i<=n;i++){
    cin>>v[i]>>w[i]>>p;   //体积,价值,依赖的物品编号
    if(p==-1) root=i;
    else add(p,i);
  }
  dfs(root);
  cout<<f[root][V];
}