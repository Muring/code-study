#include <string>
#include <vector>

using namespace std;

string solution(string my_string, int n) {
    string answer = "";
    int length = my_string.length();    // 문자열 길이 저장
    // 문자열의 길이만큼 뒤에서부터의 시작 위치를 구하고 반복하여 저장
    for(int idx = length - n; idx < length; idx++){
        answer += my_string[idx];   
    }
    return answer;
}