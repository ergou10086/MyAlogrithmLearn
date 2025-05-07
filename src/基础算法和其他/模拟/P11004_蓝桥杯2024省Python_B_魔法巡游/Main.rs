use std::io::{self, Read};

fn has_valid_digit(mut num: i32) -> bool {
    if num == 0 {
        return true;
    }
    while num > 0 {
        let digit = num % 10;
        if digit == 0 || digit == 2 || digit == 4 {
            return true;
        }
        num /= 10;
    }
    false
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut tokens = input.split_whitespace();

    let n: usize = tokens.next().unwrap().parse().unwrap();

    let mut si = vec![0; n + 1];
    let mut ti = vec![0; n + 1];

    for i in 1..=n {
        si[i] = tokens.next().unwrap().parse().unwrap();
    }

    for i in 1..=n {
        ti[i] = tokens.next().unwrap().parse().unwrap();
    }

    let mut inp = 1;
    let mut count = 0;

    for i in 1..=n {
        let temp = if inp % 2 == 1 { si[i] } else { ti[i] };

        if has_valid_digit(temp) {
            count += 1;
            inp += 1;
        }
    }

    println!("{}", count);
}