#include <string>
#include <vector>

using namespace std;

string solution(string code) {
    string answer = "";
    int mode = 0;
    for(int idx = 0; idx < code.length(); idx++){
        if(mode == 0){
            if(code[idx] == '1'){
                mode = 1;
            }
            else if(code[idx] != '1' && idx % 2 == 0)
                answer += code[idx];
        }
        else{
            if(code[idx] == '1'){
                mode = 0;
            }
            else if(code[idx] != '1' && idx % 2 != 0){
                answer += code[idx];
            }
        }
    }
    if(answer == "")
        answer = "EMPTY";
    return answer;
}