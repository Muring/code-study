#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list, int n) {
    vector<int> answer;
    for(int idx = n - 1; idx < num_list.size(); idx++){
        answer.push_back(num_list[idx]);
    }
    return answer;
}