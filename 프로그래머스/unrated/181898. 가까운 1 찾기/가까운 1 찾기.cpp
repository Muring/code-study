#include <string>
#include <vector>

using namespace std;

int solution(vector<int> arr, int idx) {
    int answer = 0;
    int flag = 0;
    for(int v_idx = idx; v_idx < arr.size(); v_idx++){
        // 해당하는 인덱스를 찾은 경우
        if(arr.at(v_idx) == 1){
            answer = v_idx;
            flag++;
            break;
        }
    }
    
    // 해당하는 인덱스가 없는 경우
    if(flag == 0)
        answer = -1;
    return answer;
}