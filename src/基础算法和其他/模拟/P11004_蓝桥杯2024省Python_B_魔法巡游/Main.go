package main

import (
    "fmt"
)

func checkNumber(num int) bool {
    if num == 0 {
        return true
    }
    n := num
    for n > 0 {
        digit := n % 10
        if digit == 0 || digit == 2 || digit == 4 {
            return true
        }
        n /= 10
    }
    return false
}

func main() {
    var n int
    fmt.Scan(&n)

    si := make([]int, n+1)
    ti := make([]int, n+1)

    for i := 1; i <= n; i++ {
        fmt.Scan(&si[i])
    }
    for i := 1; i <= n; i++ {
        fmt.Scan(&ti[i])
    }

    inp := 1
    count := 0

    for i := 1; i <= n; i++ {
        var temp int
        if inp%2 == 1 {
            temp = si[i]
        } else {
            temp = ti[i]
        }

        if checkNumber(temp) {
            count++
            inp++
        }
    }

    fmt.Println(count)
}