const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const INF = 1e9;
const N = 20;
let n, m, ans = INF;
let minv = new Array(N).fill(0);
let mins = new Array(N).fill(0);

function dfs(u, R, H, v, s) {
    if (u === 0) {
        if (v === n) ans = Math.min(ans, s);
        return;
    }
    if (v + minv[u] > n) return;
    if (s + mins[u] >= ans) return;
    if (R !== 0 && s + 2 * (n - v) / R >= ans) return;

    const maxR = Math.min(R - 1, Math.floor(Math.sqrt(n - v)));
    for (let r = maxR; r >= u; r--) {
        const maxH = Math.min(H - 1, Math.floor((n - v) / (r * r)));
        for (let h = maxH; h >= u; h--) {
            dfs(u - 1, r, h, v + r * r * h, s + 2 * r * h + (u === m ? r * r : 0));
        }
    }
}

rl.question('', (input) => {
    const parts = input.split(' ').map(Number);
    n = parts[0];
    m = parts[1];

    for (let i = 1; i <= m; i++) {
        minv[i] = minv[i - 1] + i * i * i;
        mins[i] = mins[i - 1] + 2 * i * i;
    }

    dfs(m, INF, INF, 0, 0);
    if (ans === INF) ans = 0;
    console.log(ans);
    rl.close();
});