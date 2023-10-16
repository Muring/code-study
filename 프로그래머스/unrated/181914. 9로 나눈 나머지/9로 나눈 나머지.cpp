#include <string>
#include <vector>

using namespace std;

int solution(string number) {
    int answer = 0;
    for(int idx = 0; idx < number.length(); idx++){
        answer += number[idx] - '0';
    }
    return answer % 9;
}