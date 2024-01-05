#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer = arr;
    
    for(int query_idx = 0; query_idx < queries.size(); query_idx++) {
        int start = queries[query_idx][0];
        int end = queries[query_idx][1];
        
        for(int idx = start; idx <= end; idx++) {
            answer[idx] += 1;
        }
    }
    return answer;
}