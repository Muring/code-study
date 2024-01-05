#include <string>
#include <vector>

using namespace std;

vector<string> solution(vector<string> names) {
    vector<string> answer;
    for(int idx = 0; idx < names.size(); idx += 5) {
        answer.push_back(names[idx]);
    }
    return answer;
}