#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> answer;
    int count = 0;
    for(int idx = 0; idx < num_list.size(); idx++) {
        if(num_list[idx] % 2 == 0) {
            count++;
        }
    }
    answer.push_back(count);
    answer.push_back(num_list.size() - count);
    return answer;
}