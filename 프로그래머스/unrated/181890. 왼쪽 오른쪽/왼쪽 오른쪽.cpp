#include <string>
#include <vector>

using namespace std;

vector<string> solution(vector<string> str_list) {
    vector<string> answer;
    
    for(int idx = 0; idx < str_list.size(); idx++) {
        if(str_list[idx] == "l") {
            for(int l_idx = 0; l_idx < idx; l_idx++) {
                answer.push_back(str_list[l_idx]);
            }
            break;
        }
        else if(str_list[idx] == "r") {
            for(int r_idx = idx + 1; r_idx < str_list.size(); r_idx++) {
                answer.push_back(str_list[r_idx]);
            }
            break;
        }
    }
    return answer;
}