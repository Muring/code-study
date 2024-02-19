#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int max_answer = 1;
    int factorial = 1;
    int answer = 1;
    int answer_idx = 1;
    while (answer <= n) {
        answer = 1;
        for (int idx = factorial; idx > 1; idx--) {
            answer *= idx;
        }
        if (answer > n) break;
        if (answer > max_answer) {
            max_answer = answer;
            answer_idx = factorial;
        }
        factorial++;
    }

    return answer_idx;
}