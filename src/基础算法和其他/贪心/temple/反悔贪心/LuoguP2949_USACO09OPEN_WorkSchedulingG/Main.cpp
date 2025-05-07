#include<queue>
#include<cstdio>
#include<iostream>
#include<algorithm>
using namespace std;

struct node{
    int d,p;
    bool operator<(node &b){
      return d<b.d;
    }
}w[100005];
long long ans;
priority_queue<int,vector<int>
                ,greater<int> > q;

int main(){
  int n; scanf("%d",&n);
  for(int i=1; i<=n; i++)
  scanf("%d%d",&w[i].d,&w[i].p);
  sort(w+1,w+n+1); //按截止时间排序

  for(int i=1; i<=n; i++){
    if(w[i].d==q.size()){ //截止时间相同
      if(w[i].p>q.top()){ //利润更大
        ans-=q.top();   //反悔
        q.pop();
        q.push(w[i].p);
        ans+=w[i].p;    //贪心
      }
    }
    else{ //截止时间大
      q.push(w[i].p);
      ans+=w[i].p;    //贪心
    }
  }
  printf("%lld",ans);
  return 0;
}