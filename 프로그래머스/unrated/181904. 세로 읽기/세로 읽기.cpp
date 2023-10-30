#include <string>
#include <vector>

using namespace std;

string solution(string my_string, int m, int c) {
    string answer = "";
    for(int idx = 0; idx < my_string.length(); idx++) {
        if(idx % m == c - 1) {
            answer += my_string[idx];
        }
    }
    return answer;
}