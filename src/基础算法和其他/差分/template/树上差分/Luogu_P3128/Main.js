const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let maxn = 300050;
let maxm = maxn << 1;
let n;
let a = [];
let head = [];
let cnt = 0;
let fa = [];
let dep = [];
let amounts = [];
let edge = [];

function addEdge(u, v) {
    cnt++;
    edge[cnt] = {
        u: u,
        v: v,
        next: head[u]
    };
    head[u] = cnt;

    cnt++;
    edge[cnt] = {
        u: v,
        v: u,
        next: head[v]
    };
    head[v] = cnt;
}

function dfs(u, faa) {
    fa[u][0] = faa;
    dep[u] = dep[faa] + 1;

    for (let i = 1; i <= 30; i++) {
        fa[u][i] = fa[fa[u][i - 1]][i - 1];
    }

    for (let i = head[u]; i!== 0; i = edge[i].next) {
        let v = edge[i].v;
        if (v === faa) continue;
        dfs(v, u);
    }
}

function lca(x, y) {
    if (dep[x] < dep[y]) {
        let temp = x;
        x = y;
        y = temp;
    }

    for (let i = 30; i >= 0; i--) {
        if (dep[fa[x][i]] >= dep[y]) {
            x = fa[x][i];
        }
    }

    if (x === y) return x;

    for (let i = 30; i >= 0; i--) {
        if (fa[x][i]!== fa[y][i]) {
            x = fa[x][i];
            y = fa[y][i];
        }
    }

    return fa[x][0];
}

function dfs2(u, faa) {
    for (let i = head[u]; i!== 0; i = edge[i].next) {
        let v = edge[i].v;
        if (v === faa) continue;
        dfs2(v, u);
        amounts[u] += amounts[v];
    }
}

rl.question('', (input) => {
    n = parseInt(input);

    a = new Array(n + 1).fill(0);
    head = new Array(n + 1).fill(0);
    fa = new Array(n + 1).fill(0).map(() => new Array(31).fill(0));
    dep = new Array(n + 1).fill(0);
    amounts = new Array(n + 1).fill(0);
    edge = new Array(maxm + 1).fill(0);

    rl.question('', (input) => {
        let arr = input.split(' ').map(Number);
        for (let i = 1; i <= n; i++) {
            a[i] = arr[i - 1];
        }

        for (let i = 1; i < n; i++) {
            rl.question('', (input) => {
                let [x, y] = input.split(' ').map(Number);
                addEdge(x, y);
                addEdge(y, x);

                if (i === n - 1) {
                    dfs(1, 0);

                    for (let i = 1; i <= n - 1; i++) {
                        let us = a[i];
                        let vs = a[i + 1];
                        let ts = lca(us, vs);
                        amounts[us] += 1;
                        amounts[vs] += 1;
                        amounts[ts] -= 1;
                        amounts[fa[ts][0]] -= 1;
                    }

                    dfs2(1, 0);

                    for (let i = 2; i <= n; i++) {
                        amounts[a[i]]--;
                    }

                    for (let i = 1; i <= n; i++) {
                        console.log(amounts[i]);
                    }

                    rl.close();
                }
            });
        }
    });
});