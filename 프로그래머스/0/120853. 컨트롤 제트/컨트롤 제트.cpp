#include <string>
#include <vector>

using namespace std;

int solution(string s) {    
    string temp = "";
    int sum = 0;
    int pre_num = 0;
    
    for(int idx = 0; idx < s.length(); idx++) {
        if(s[idx] == ' ' && !temp.empty()) {
            pre_num = stoi(temp);
            sum += pre_num;
            temp = "";
        }
        else if(s[idx] == 'Z') {
            sum -= pre_num;
        }
        else {
            temp += s[idx];
        }
    }
    if(temp.length() != 0) {
        pre_num = stoi(temp);
        sum += pre_num;
    }
    return sum;
}