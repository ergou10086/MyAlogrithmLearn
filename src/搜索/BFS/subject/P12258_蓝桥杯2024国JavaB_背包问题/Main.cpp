#include <iostream>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <functional>
using namespace std;

typedef pair<long long, int> pii;

int main() {
    int n, k, l;
    scanf("%d %d %d", &n, &k, &l);

    // 输入并去重物品重量
    unordered_set<long long> ws;
    for (int i = 0; i < n; ++i) {
        long long x;
        scanf("%lld", &x);
        ws.insert(x);
    }
    vector<long long> unique_w(ws.begin(), ws.end());

    priority_queue<pii, vector<pii>, greater<>> pq;
    unordered_map<long long, int> max_num;
    unordered_set<long long> answer_set;

    // 初始化队列
    for (auto& w : unique_w) {
        if (!max_num.count(w) || max_num[w] < 1) {
            max_num[w] = 1;
            pq.emplace(w, 1);
        }
    }

    int count = 0;
    long long ans = -1;

    while (!pq.empty()) {
        auto [cur_wei, cur_num] = pq.top();
        pq.pop();

        // 跳过非最大num的状态
        if (max_num[cur_wei] != cur_num) continue;

        // 满足条件则加入答案集合
        if (cur_num >= k) {
            if (answer_set.insert(cur_wei).second) {
                if (++count == l) {
                    ans = cur_wei;
                    break;
                }
            }
        }

        // 生成新的状态
        for (auto& w : unique_w) {
            long long new_wei = cur_wei + w;
            int new_num = cur_num + 1;

            // 仅当新数目更大时更新
            if (!max_num.count(new_wei) || new_num > max_num[new_wei]) {
                max_num[new_wei] = new_num;
                pq.emplace(new_wei, new_num);
            }
        }
    }

    printf("%lld\n", ans);
    return 0;
}