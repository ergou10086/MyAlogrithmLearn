const N = 10005;
let n, ans = 0;
let a = new Array(N);
for (let i = 0; i < N; i++) {
    a[i] = new Array(N).fill(0);
}

// 读取输入
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let inputLines = [];
rl.on('line', (line) => {
    inputLines.push(line);
});

rl.on('close', () => {
    n = parseInt(inputLines[0]);
    for (let i = 1; i <= n; i++) {
        const [x1, y1, x2, y2] = inputLines[i].split(' ').map(Number);
        a[x1][y1]++;
        a[x1][y2]--;
        a[x2][y1]--;
        a[x2][y2]++;
    }

    // 计算二维前缀和
    for (let i = 0; i <= 10000; i++) {
        for (let j = 0; j <= 10000; j++) {
            if (i > 0 && j > 0) {
                a[i][j] += a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];
            } else if (i > 0) {
                a[i][j] += a[i - 1][j];
            } else if (j > 0) {
                a[i][j] += a[i][j - 1];
            }
            if (a[i][j] !== 0) {
                ans++;
            }
        }
    }

    // 输出结果
    console.log(ans);
});