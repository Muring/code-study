#include <string>
#include <vector>

using namespace std;

int solution(string my_string) {
    int answer = 0;
    for(int c : my_string) {
        if(0 < c - '0' &&  c - '0'  < 10) {
            answer += c - '0';
        }
    }
    return answer;
}