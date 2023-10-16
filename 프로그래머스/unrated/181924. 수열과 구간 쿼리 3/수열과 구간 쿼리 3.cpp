#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer;
    answer = arr;
    for(vector<int> query : queries){
        int temp = answer[query[0]];
        answer[query[0]] = answer[query[1]];
        answer[query[1]] = temp;
    }
    return answer;
}