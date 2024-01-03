#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> intervals) {
    vector<int> answer;
    for(int interval_idx = 0; interval_idx < intervals.size(); interval_idx++){
        int start = intervals[interval_idx][0];
        int end = intervals[interval_idx][1];
        
        for(int idx = start; idx <= end; idx++) {
            answer.push_back(arr.at(idx));
        }
    }
    return answer;
}