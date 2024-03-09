#include <string>
#include <vector>

using namespace std;

int solution(string s) {    
    string temp = "";   // string s에서 뽑아서 숫자 임시 저장 변수
    int sum = 0;        // 전체 합 저장 변수
    int pre_num = 0;    // 이전 수 저장 변수
    
    // string s의 길이만큼
    for(int idx = 0; idx < s.length(); idx++) {
        // 현재 위치가 공백이면서 temp 값이 비어있는 경우
        // temp 값이 비어있는지 확인하지 않으면 메모리가 터진다.
        if(s[idx] == ' ' && !temp.empty()) { 
            // 숫자를 더하고 pre_num에 저장 및 temp 초기화
            pre_num = stoi(temp);
            sum += pre_num;
            temp = "";
        }
        // 현재 위치가 Z인 경우 이전 수를 뺀다.
        else if(s[idx] == 'Z') {
            sum -= pre_num;
        }
        // 현재 값이 숫자인 경우
        else {
            temp += s[idx];
        }
    }
    // 만약 마지막에 끝났는데 temp가 남아있는 경우
    // 마지막 숫자는 더해지지 않은 상태이기 때문에 더해주고 종료한다.
    if(temp.length() != 0) {
        pre_num = stoi(temp);
        sum += pre_num;
    }
    // 결과 반환
    return sum;
}