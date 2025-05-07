# 特判 m=9,d=30，此时为必胜。
# 特判 m=11,d=30，此时为必胜。
# 判断 (m+d)mod2=0此时为必胜

# 也就是说，从有30号的月份的偶数天开始加是必胜
# 有31号（29号）的月份的奇数天开始加是必胜
# 因为只要隔开三天，就可以


n = int(input())
year = []
month = []
day = []
for i in range(n):
    inpday = [int(x) for x in input().split()]
    year.append(inpday[0])
    month.append(inpday[1])
    day.append(inpday[2])
for i in range(n):
    if (month[i] == 9 and day[i] == 30) or (month[i] == 11 and day[i] == 30) or ((month[i] + day[i]) % 2 == 0):
        print("YES")
    else:
        print("NO")