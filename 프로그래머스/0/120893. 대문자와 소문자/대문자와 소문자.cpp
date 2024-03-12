#include <string>
#include <vector>

using namespace std;

string solution(string my_string) {
    string answer = "";
    for(auto x : my_string) {
        if(x >= 'A' && x <= 'Z') {
            answer += tolower(x);
        }
        else {
            answer += toupper(x);
        }
    }
    return answer;
}