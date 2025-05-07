def main():
    import sys
    # 读取所有单词，假设每个单词位于单独的一行
    words = [line.strip() for line in sys.stdin if line.strip()]

    # 按长度升序、字典序升序排序
    sorted_words = sorted(words, key=lambda x: (len(x), x))

    # 动态规划字典，键是排序后的字符串，值是元组（最长长度，最小字典序原字符串）
    dp = {}

    for word in sorted_words:
        current_len = len(word)
        key = ''.join(sorted(word))

        if current_len == 1:
            # 长度为1的字符串自动成为优美字符串
            if key not in dp or word < dp[key][1]:
                dp[key] = (current_len, word)
        else:
            # 生成所有可能的父键（删除一个字符后的排序字符串）
            sorted_chars = sorted(word)
            parents = set()
            for i in range(len(sorted_chars)):
                parent = ''.join(sorted_chars[:i] + sorted_chars[i+1:])
                parents.add(parent)

            # 检查是否存在父键且其长度为当前长度减一
            found = any(p in dp and dp[p][0] == current_len - 1 for p in parents)

            if found:
                # 当前单词可以作为优美字符串
                new_len = current_len
                # 更新dp中的记录
                if key not in dp:
                    dp[key] = (new_len, word)
                else:
                    existing_len, existing_word = dp[key]
                    if new_len > existing_len or (new_len == existing_len and word < existing_word):
                        dp[key] = (new_len, word)

    # 找到最长的结果，字典序最小的
    max_len = 0
    result = ''
    for key in dp:
        current_len, current_word = dp[key]
        if current_len > max_len or (current_len == max_len and current_word < result):
            max_len = current_len
            result = current_word

    print(result)

if __name__ == "__main__":
    main()