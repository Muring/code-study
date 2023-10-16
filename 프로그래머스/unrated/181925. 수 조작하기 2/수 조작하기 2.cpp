#include <string>
#include <vector>

using namespace std;

string solution(vector<int> numLog) {
    string answer = "";
    for(int idx = 1; idx <= numLog.size() - 1; idx++){
        int diff = numLog[idx] - numLog[idx - 1];
        if(diff == 1){
            answer += 'w';
        }
        else if(diff == -1){
            answer += 's';
        }
        else if(diff == 10){
            answer += 'd';
        }
        else if(diff == -10){
            answer += 'a';
        }
    }
    return answer;
}