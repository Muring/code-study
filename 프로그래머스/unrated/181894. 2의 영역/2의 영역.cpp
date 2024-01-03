#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr) {
    vector<int> answer;
    int start = -1, end = -1;
    int flag = 0;
    
    // Find start point and end point in arr
    for(int idx = 0; idx < arr.size(); idx++) {
        if(arr.at(idx) == 2) {
            if(flag == 0) {
                flag++;
                start = idx;
            }
            else if(flag == 1){
                end = idx;
            }
        }
    }
    
    // If there is no number 2
    if(start == -1) {
        answer.push_back(-1);
    }
    else {
        // If there is only one number 2
        if(start != -1 && end == -1) {
            end = start;
        }
        // If there are two number 2
        for(int idx = start; idx <= end; idx++){
            answer.push_back(arr.at(idx));
        }
    }
    

    return answer;
}