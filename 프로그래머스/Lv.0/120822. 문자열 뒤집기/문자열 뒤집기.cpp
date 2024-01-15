#include <string>
#include <vector>

using namespace std;

string solution(string my_string) {
    string answer = "";
    for(int idx = my_string.size() - 1; idx >= 0; idx--) {
        answer.push_back(my_string[idx]);
    }
    return answer;
}