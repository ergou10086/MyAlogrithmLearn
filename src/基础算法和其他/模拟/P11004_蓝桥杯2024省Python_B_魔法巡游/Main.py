def main():
    import sys
    input = sys.stdin.read().split()
    ptr = 0
    n = int(input[ptr])
    ptr += 1

    si = [0] * (n + 1)
    ti = [0] * (n + 1)

    for i in range(1, n+1):
        si[i] = int(input[ptr])
        ptr += 1

    for i in range(1, n+1):
        ti[i] = int(input[ptr])
        ptr += 1

    inp = 1
    count = 0

    for i in range(1, n+1):
        flag = False
        if inp % 2 == 1:
            temp = si[i]
        else:
            temp = ti[i]

        if temp == 0:
            flag = True
        else:
            num = temp
            while num > 0:
                digit = num % 10
                if digit in (0, 2, 4):
                    flag = True
                    break
                num = num // 10

        if flag:
            count += 1
            inp += 1

    print(count)

if __name__ == "__main__":
    main()