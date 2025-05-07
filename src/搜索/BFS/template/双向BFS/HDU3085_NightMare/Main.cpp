#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#define x first
#define y second
using namespace std;

const int N=810;
int n,m;
char g[N][N];  //地图
int vis[N][N]; //2表示女孩走过,1表示男孩走过,0都没走过
pair<int,int> boy,girl,ghost[2]; //存储人、鬼的初始位置
int dx[4]={-1,0,1,0},dy[4]={0,1,0,-1}; //搜索的方向数组

bool check(int x,int y,int tim){
  if(x<0||x>=n||y<0||y>=m||g[x][y]=='X') return false;
  for(int i=0;i<2;i++)
    if(abs(x-ghost[i].x)+abs(y-ghost[i].y)<=tim*2)return false;
  return true; //(x,y)合法则返回true
}
int bfs(){
  int tim=0;
  memset(vis,0,sizeof vis);
  queue<pair<int,int>> qb,qg;
  qb.push(boy); qg.push(girl);
  while(qb.size()||qg.size()){
    tim++; //增加1秒
    for(int i=0;i<3;i++) //男孩走3步
      for(int j=0,s=qb.size();j<s;j++){ //枚举队中所有点
        pair<int,int> t=qb.front(); qb.pop();
        int x=t.x, y=t.y;
        if(!check(x,y,tim)) continue;  //(x,y)非法则跳过
        for(int k=0;k<4;k++){          //4个方向
          int a=x+dx[k], b=y+dy[k];
          if(check(a,b,tim)){            //(a,b)合法
            if(vis[a][b]==2) return tim; //2表示女孩走过
            if(!vis[a][b]) vis[a][b]=1,qb.push({a,b});
          }
        }
      }
    for(int i=0;i<1;i++) //女孩走1步
      for(int j=0,s=qg.size();j<s;j++){
        pair<int,int> t=qg.front();qg.pop();
        int x=t.x, y=t.y;
        if(!check(x,y,tim)) continue;
        for(int k=0;k<4;k++){
          int a=x+dx[k], b=y+dy[k];
          if(check(a,b,tim)){
            if(vis[a][b]==1) return tim; //1表示男孩走过
            if(!vis[a][b]) vis[a][b]=2,qg.push({a,b});
          }
        }
      }
  }
  return -1; //无解返回-1
}
int main(){
  int T;scanf("%d",&T);
  while(T--){
    scanf("%d%d",&n,&m);
    for(int i=0;i<n;i++) scanf("%s",g[i]);
    for(int i=0,t=0;i<n;i++) //找出人、鬼的位置
      for(int j=0;j<m;j++)
        if(g[i][j]=='M') boy={i,j};
        else if(g[i][j]=='G') girl={i,j};
        else if(g[i][j]=='Z') ghost[t++]={i,j};
    printf("%d\n",bfs());
  }
}