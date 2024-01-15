#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    for(int idx = 2; idx <= n; idx++) {
        if(idx % 2 == 0) {
            answer += idx;
        }
    }
    return answer;
}