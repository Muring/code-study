#include <string>
#include <vector>

using namespace std;

int solution(vector<int> array) {
    int answer = 0;
    // 먼저 배열을 확인하여 각 숫자의 개수들을 모두 dp 배열에 저장한다.
    int dp[1000] = {0, };
    for(auto x : array) {
        dp[x]++;
    }

    // 구해진 dp 배열에서 최빈 값을 구한다.
    // 이때, 최빈값이 여러개면 -1 반환
    int max_idx = 0;
    int flag = 0;
    for(int idx = 1; idx < 1000; idx++) {
        if(dp[max_idx] < dp[idx]) {
            max_idx = idx;
            flag = 0;
        }
        else if(dp[max_idx] == dp[idx])
            flag++;
    }
    answer = flag == 0 ? max_idx : -1;
    return answer;
}