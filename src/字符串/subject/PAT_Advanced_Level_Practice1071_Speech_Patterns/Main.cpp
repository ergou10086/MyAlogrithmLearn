#include <iostream>
#include <string>
#include <unordered_map>
#include <cctype>

class Solutions {
public:
    Solutions() {
        std::string line;
        std::getline(std::cin, line);

        std::unordered_map<std::string, int> wordCount;
        std::string currentWord;

        for (char c : line) {
            if (std::isalnum(c)) {
                currentWord += std::tolower(c);
            } else {
                if (!currentWord.empty()) {
                    wordCount[currentWord]++;
                    currentWord.clear();
                }
            }
        }

        // 处理最后一个单词
        if (!currentWord.empty()) {
            wordCount[currentWord]++;
        }

        std::string result;
        int maxCount = 0;
        for (const auto& entry : wordCount) {
            if (entry.second > maxCount ||
                (entry.second == maxCount && entry.first < result)) {
                maxCount = entry.second;
                result = entry.first;
            }
        }

        std::cout << result << " " << maxCount << std::endl;
    }
};

int main() {
    Solutions sol;
    return 0;
}