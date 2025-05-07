import datetime

def contains_one(date):
    # 检查年月日是否包含1
    date_str = date.strftime("%Y%m%d")
    if '1' in date_str:
        return True

    # 检查星期是否包含1（星期一对应0或1，取决于系统）
    # Python中周一为0或1，这里假设周一为0（isoweekday()周一为1）
    if date.isoweekday() == 1:  # isoweekday()周一=1
        return True

    return False

def calculate_running_km():
    total_km = 0
    date = datetime.date(2023, 1, 1)

    while date.year == 2023:
        if contains_one(date):
            total_km += 5
        else:
            total_km += 1
        date += datetime.timedelta(days=1)

    return total_km

if __name__ == "__main__":
    print(calculate_running_km())