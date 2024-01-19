#include <string>
#include <vector>

using namespace std;

double factorial(int num, int len) {
    double answer = 1;
    for (int idx = 0; idx < len; idx++) {
        answer *= num - idx;
    }
    return answer;
}

int solution(int balls, int share) { return factorial(balls, share) / factorial(share, share); }
