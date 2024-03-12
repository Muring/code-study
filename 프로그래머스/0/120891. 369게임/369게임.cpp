#include <string>
#include <vector>

using namespace std;

int solution(int order) {
    int answer = 0;
    
    string str = to_string(order);
    for(auto x : str) {
        if(x == '3' || x == '6' || x == '9')
            answer++;
    }
    
    return answer;
}