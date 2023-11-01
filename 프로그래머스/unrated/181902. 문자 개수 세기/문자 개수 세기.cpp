#include <string>
#include <vector>

using namespace std;

vector<int> solution(string my_string) {
    vector<int> answer(52);
    char ch;
    for(int idx = 0; idx < my_string.length(); idx++) {
        ch = my_string[idx];
        if(65 <= ch && ch <= 90) {
            answer[ch - 65] += 1;
        }
        else if(97 <= ch && ch <= 122){
            answer[ch - 71] += 1;
        }
    }

    return answer;
}