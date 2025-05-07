package PAT_Advanced_Level_Practice1117_EddingtonNumber

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	n, _ := strconv.Atoi(scanner.Text())

	arr := make([]int, n)
	scanner.Scan()
	numbers := strings.Fields(scanner.Text())
	for i := 0; i < n; i++ {
		arr[i], _ = strconv.Atoi(numbers[i])
	}

	sort.Ints(arr)

	for j, ans := n-1, 1; j >= 0; j, ans = j-1, ans+1 {
		if arr[j] <= ans {
			fmt.Println(ans - 1)
			break
		}
	}
}