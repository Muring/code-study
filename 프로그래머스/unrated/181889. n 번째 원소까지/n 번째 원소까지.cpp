#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list, int n) {
    vector<int> answer;
    for(int idx = 0; idx < n; idx++) {
        answer.push_back(num_list[idx]);
    }
    return answer;
}