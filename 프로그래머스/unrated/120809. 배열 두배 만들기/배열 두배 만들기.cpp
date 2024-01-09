#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer;
    for(auto x : numbers) {
        answer.push_back(x * 2);
    }
    return answer;
}