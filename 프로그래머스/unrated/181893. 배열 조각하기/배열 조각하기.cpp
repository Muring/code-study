#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<int> query) {
    vector<int> answer;
    answer = arr;
    for(int idx = 0; idx < query.size(); idx++){
        // 짝수 인덱스 -> 뒷부분을 자른다.
        if(idx % 2 == 0) {
            answer.erase(answer.begin() + query[idx] + 1, answer.begin() + answer.size());
        }
        // 홀수 인덱스 -> 앞부분을 자른다.
        else if(idx % 2 != 0) {
            answer.erase(answer.begin(), answer.begin() + query[idx]);
        }
    }
    return answer;
}