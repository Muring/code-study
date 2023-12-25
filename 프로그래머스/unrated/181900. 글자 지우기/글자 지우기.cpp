#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string my_string, vector<int> indices) {
    string answer = "";
    sort(indices.begin(), indices.end());
    int v_index = 0;
    for(int idx = 0; idx < my_string.length(); idx++){
        if(idx == indices[v_index]){
            v_index++;
            continue;
        }
        answer += my_string[idx];
    }
    return answer;
}