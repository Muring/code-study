#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> numbers, string direction) {
    vector<int> answer;
    
    int length = numbers.size();
    if(direction == "right") {
        answer.push_back(numbers.back());
        for(int idx = 0; idx < length - 1; idx++) {
            answer.push_back(numbers[idx]);
        }
    }
    else {
        for(int idx = 1; idx < length; idx++) {
            answer.push_back(numbers[idx]);
        }
        answer.push_back(numbers.front());
    }
    return answer;
}