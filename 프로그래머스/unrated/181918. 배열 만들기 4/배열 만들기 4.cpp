#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr) {
    vector<int> stk;
    int idx = 0;
    while(idx < arr.size()){
        if(stk.size() == 0){
            stk.push_back(arr[idx++]);
        }
        else if(stk.size() != 0 && stk[stk.size() - 1] < arr[idx])
            stk.push_back(arr[idx++]);
        else if(stk.size() != 0 && stk[stk.size() - 1] >= arr[idx])
            stk.pop_back();
    }
    return stk;
}