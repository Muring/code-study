#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer;
    for(auto query : queries){
        for(int idx = 0; idx < arr.size(); idx++){
            if(query[0] <= idx && idx <= query[1] && idx % query[2] == 0)
                arr[idx] += 1;
        }
    }
    return arr;
}