function check(num: number): boolean {
    if (num === 0) return true;
    let n = num;
    while (n > 0) {
        const digit = n % 10;
        if (digit === 0 || digit === 2 || digit === 4) return true;
        n = Math.floor(n / 10);
    }
    return false;
}

function main(): void {
    // @ts-ignore
    const fs = require('fs');
    const input = fs.readFileSync(0, 'utf-8').split(/\s+/);
    let ptr = 0;
    const n = parseInt(input[ptr++]);

    // @ts-ignore
    const si: number[] = new Array(n + 1).fill(0);
    // @ts-ignore
    const ti: number[] = new Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) si[i] = parseInt(input[ptr++]);
    for (let i = 1; i <= n; i++) ti[i] = parseInt(input[ptr++]);

    let inp = 1, count = 0;
    for (let i = 1; i <= n; i++) {
        const temp = (inp % 2 === 1) ? si[i] : ti[i];
        if (check(temp)) {
            count++;
            inp++;
        }
    }
    console.log(count);
}

main();