#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    int start = 0, end = 0;     // 자르는 배열의 시작과 끝 인덱스
    int k_idx = 0;              // 정답 위치 인덱스
    // 전체 commands의 갯수만큼 반복한다.
    for(int comm_idx = 0; comm_idx < commands.size(); comm_idx++) {
        
        start = commands[comm_idx][0];
        end = commands[comm_idx][1];
        k_idx = commands[comm_idx][2];
        
        // 1. 배열 자르기
        vector<int> temp(array.begin() + start - 1, array.begin() + end);
    
        // 2. 배열 정렬
        sort(temp.begin(), temp.end());
    
        // 3. K번째 숫자가 정답이다.
        answer.push_back(temp[k_idx - 1]);
    }
    
    
    return answer;
}