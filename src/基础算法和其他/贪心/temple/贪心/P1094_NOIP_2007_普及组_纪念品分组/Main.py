w = int(input())
n = int(input())
price = []
for _ in range(n):
    line = int(input())
    price.append(line)

price.sort()
l, r = 0, n-1
result = 0
while l <= r:
    if l == r:
        # 落单的单独一组
        result += 1
        break
    if price[l] + price[r] <= w:
        l += 1
        r -= 1
        result += 1
    else:
        result += 1
        r -= 1

print(result)