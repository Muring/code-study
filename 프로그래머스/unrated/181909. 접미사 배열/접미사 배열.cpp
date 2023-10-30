#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> solution(string my_string) {
    vector<string> answer;
    int len = my_string.length();
    for(int idx = 0; idx < len; idx++){
        string temp = "";
        for(int i = len - 1 - idx; i <= len - 1; i++){
            temp += my_string[i];
        }
        answer.push_back(temp);
    }
    sort(answer.begin(), answer.end());
    return answer;
}