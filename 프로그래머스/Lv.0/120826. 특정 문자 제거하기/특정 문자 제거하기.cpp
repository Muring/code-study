#include <string>
#include <string.h>
#include <vector>

using namespace std;

string solution(string my_string, string letter) {
    string answer = "";
    char ch = letter[0];
    for(auto x : my_string) {
        if(x != ch) {
            answer += x;
        }
    }
    return answer;
}