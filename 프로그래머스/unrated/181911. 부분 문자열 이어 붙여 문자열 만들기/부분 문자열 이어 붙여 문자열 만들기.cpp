#include <string>
#include <vector>

using namespace std;

string solution(vector<string> my_strings, vector<vector<int>> parts) {
    string answer = "";
    for(int my_string = 0; my_string < parts.size(); my_string++){
        for(int idx = parts[my_string][0]; idx <= parts[my_string][1]; idx++){
            answer += my_strings[my_string][idx];
        }
    }
    return answer;
}