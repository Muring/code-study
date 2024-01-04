#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list, int n) {
    vector<int> answer;
    for(int idx = n; idx < num_list.size() + n; idx++){
        if(idx >= num_list.size()) {
            answer.push_back(num_list[idx - num_list.size()]);
            continue;
        }
        answer.push_back(num_list[idx]);
        
    }
    return answer;
}