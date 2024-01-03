#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int flag = 0;
    for(int idx = 0; idx < num_list.size(); idx++) {
        if(num_list.at(idx) < 0) {
            answer = idx;
            flag++;
            break;
        }
    }
    
    if(flag == 0) {
        answer = -1;
    }
    return answer;
}