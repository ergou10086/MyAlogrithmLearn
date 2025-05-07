import sys
from collections import defaultdict, deque

si = 205

def read():
    x, f = 0, 1
    ch = sys.stdin.read(1)
    while ch < '0' or ch > '9':
        if ch == '-':
            f = -1
        ch = sys.stdin.read(1)
    while ch >= '0' and ch <= '9':
        x = (x << 1) + (x << 3) + (ord(ch) ^ 48)
        ch = sys.stdin.read(1)
    return x * f

def tarjan(x):
    global num, numb
    dfn[x] = low[x] = num
    num += 1
    ins[x] = True
    st.append(x)
    for q in map[x]:
        if dfn[q] == 0:
            tarjan(q)
            low[x] = min(low[x], low[q])
        elif ins[q]:
            low[x] = min(low[x], dfn[q])
    if dfn[x] == low[x]:
        numb += 1
        while True:
            p = st.pop()
            ins[p] = False
            bl[p] = numb
            nums[numb] += 1
            if x == p:
                break

n = read()
a = 1
map = defaultdict(list)
for i in range(1, n + 1):
    while a != 0:
        a = read()
        if a == 0:
            continue
        map[i].append(a)
    a = 1

dfn = [0] * si
low = [0] * si
ins = [False] * si
bl = [0] * si
nums = [0] * si
ru = [0] * si
st = []
num = 1
numb = 0
tot = 0

for i in range(1, n + 1):
    if dfn[i] == 0:
        tarjan(i)

for i in range(1, n + 1):
    for next in map[i]:
        if bl[i] != bl[next]:
            ru[bl[next]] += 1

for i in range(1, numb + 1):
    if ru[i] == 0:
        tot += 1

print(tot)