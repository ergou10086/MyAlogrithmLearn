n = int(input())
header = input().strip().split(',')
data_rows = [input().strip().split(',') for _ in range(n-1)]

col_index = {name: idx for idx, name in enumerate(header)}
col_types = {}

# 确定各列的类型
for name in header:
    idx = col_index[name]
    if data_rows:  # 有数据行
        cell = data_rows[0][idx]
        if cell.isdigit():
            col_types[name] = 'num'
        else:
            col_types[name] = 'str'

m = int(input())
sort_conditions = []
for _ in range(m):
    s = input().strip()
    name_part = s[:-1]
    order = s[-1]
    sort_conditions.append((name_part, order))

# 生成排序键函数
def get_key(row):
    key = []
    for (name, order) in sort_conditions:
        idx = col_index[name]
        cell_value = row[idx]
        col_type = col_types[name]
        if col_type == 'num':
            num = int(cell_value)
            key_elem = -num if order == '-' else num
        else:
            if order == '-':
                key_elem = tuple(-ord(c) for c in cell_value)
            else:
                key_elem = cell_value
        key.append(key_elem)
    return tuple(key)

# 排序数据行
sorted_data = sorted(data_rows, key=get_key)

# 输出结果
print(','.join(header))
for row in sorted_data:
    print(','.join(row))