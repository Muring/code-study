#include <string>
#include <vector>

using namespace std;

string solution(string my_string, int n) {
    string answer = "";
    for(int idx = 0; idx < n; idx++)
        answer += my_string[idx];
    return answer;
}