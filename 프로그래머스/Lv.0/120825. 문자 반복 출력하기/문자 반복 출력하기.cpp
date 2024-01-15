#include <string>
#include <vector>

using namespace std;

string solution(string my_string, int n) {
    string answer = "";
    for(int idx = 0; idx < my_string.size(); idx++) {
        for(int string_idx = 0; string_idx < n; string_idx++) {
            answer += my_string[idx];
        }
    }
    return answer;
}