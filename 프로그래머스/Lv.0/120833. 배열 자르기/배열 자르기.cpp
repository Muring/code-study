#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> numbers, int num1, int num2) {
    vector<int> answer;
    for(int idx = num1; idx <= num2; idx++) {
        answer.push_back(numbers[idx]);
    }
    return answer;
}