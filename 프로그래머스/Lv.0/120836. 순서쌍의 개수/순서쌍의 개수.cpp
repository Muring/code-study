#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    // 숫자 n을 소인수분해한다.
    int count = 0;
    for(int idx = 1; idx <= n; idx++) {
        if(n % idx == 0) {
            count++;
        }
    }
    // 소인수 분해한 것을 토대로 개수를 센다.
    answer = count;
    return answer;
}