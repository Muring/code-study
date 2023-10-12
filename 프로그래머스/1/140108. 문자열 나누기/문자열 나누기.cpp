#include <string>
#include <vector>

using namespace std;

int solution(string s) {
    int answer = 0;
    int count = 0;
    char ch;
    for (auto x : s) {
        if(count == 0){
            ch = x;
            answer++;
        }
        if(x == ch)
            count++;
        else
            count--;
        
            
    }
    // if(count != 0)
    //     answer++;
    return answer;
}