#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer;
    for(auto query : queries){
        int min_answer = 1000001;
        for(int idx = query[0]; idx <= query[1]; idx++){
            if(arr[idx] > query[2]){
                min_answer = arr[idx] < min_answer ? arr[idx] : min_answer;
            }
        }
        if(min_answer == 1000001)
            min_answer = -1;
        answer.push_back(min_answer);
    }
    return answer;
}