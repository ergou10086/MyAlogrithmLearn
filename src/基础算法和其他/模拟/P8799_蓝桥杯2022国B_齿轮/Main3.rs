use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let mut iter = input.split_whitespace();
    let n: usize = iter.next().unwrap().parse().unwrap();
    let q: usize = iter.next().unwrap().parse().unwrap();

    let mut bucket = vec![0; 200005];
    input.clear();
    io::stdin().read_line(&mut input).unwrap();
    for num in input.split_whitespace() {
        let x: usize = num.parse().unwrap();
        bucket[x] += 1;
    }

    let mut ans = vec![0; 200005];

    for i in 1..=200000 {
        if bucket[i] >= 2 {
            ans[1] = 1;
            break;
        }
    }

    // 预处理所有可能的比值
    for i in 2..=200000 {
        for j in 1..=(200000 / i) {
            if bucket[j] != 0 && bucket[j * i] != 0 {
                ans[i] = 1;
                break;
            }
        }
    }

    // 处理查询
    for _ in 0..q {
        input.clear();
        io::stdin().read_line(&mut input).unwrap();
        let x: usize = input.trim().parse().unwrap();
        if ans[x] == 1 {
            println!("YES");
        } else {
            println!("NO");
        }
    }
}