#include <string>
#include <vector>

using namespace std;

int solution(int a, int d, vector<bool> included) {
    int answer = 0;
    // 등차수열을 구하고 배열에 저장하며 included에서 해당 배열의 인덱스의 값이 true일 때 해당 등차수열 값을 더해 결과에 저장한다.
    for(int idx = 0; idx < included.size(); idx++){
        if(included[idx] == true){
            answer += a;
        }
        a += d;
    
    }
    return answer;
}