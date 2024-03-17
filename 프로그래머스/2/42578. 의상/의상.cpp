#include <string>
#include <vector>
#include <map>

using namespace std;

// 아무것도 입지 않은 경우의 수를 제외하고는 모든 경우의 수가 성립한다.
// 따라서 각 옷의 개수 + 1을 모두 곱한 후 모두 벗은 경우의 수를 빼면 정답이다.

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    map<string, int> m;

    // map에서 초기화를 하지 않고 ++을 하는 경우에는 자동으로 0으로 초기화 된 후 ++연산이 진행된다.
    for(auto cloth : clothes)
        m[cloth[1]] += 1;
    
    for(auto i = m.begin(); i != m.end(); i++)
        answer *= i->second + 1;

    return answer - 1;
}