#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> answer;
    answer = num_list;
    int idx = num_list.size() - 1;
    if(num_list[idx] > num_list[idx - 1])
        answer.push_back(num_list[idx] - num_list[idx - 1]);
    else
        answer.push_back(num_list[idx] * 2);
    return answer;
}