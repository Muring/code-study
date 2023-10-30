#include <string>
#include <vector>

using namespace std;

int solution(string my_string, string is_prefix) {
    int answer = 0;
    for(int len = 0; len < my_string.length(); len++){
        string temp = "";
        for(int idx = 0; idx <= len; idx++){
            temp += my_string[idx];
        }
        if(temp.compare(is_prefix) == 0){
            answer = 1;
            break;
        }
    }
    return answer;
}