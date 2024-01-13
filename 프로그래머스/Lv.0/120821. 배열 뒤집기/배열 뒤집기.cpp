#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> answer;
    for(int idx = num_list.size() - 1; idx >= 0; idx--) {
        answer.push_back(num_list[idx]);
    }
    return answer;
}