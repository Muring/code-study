#include <string>
#include <vector>

using namespace std;

vector<string> solution(vector<string> todo_list, vector<bool> finished) {
    vector<string> answer;
    for(int idx = 0; idx < todo_list.size(); idx++) {
        if(finished[idx] == false) {
            answer.push_back(todo_list[idx]);
        }
    }
    return answer;
}