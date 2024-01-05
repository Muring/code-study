#include <string>
#include <vector>

using namespace std;

int solution(vector<int> numbers, int n) {
    int answer = 0;
    
    for(int idx = 0; idx < numbers.size(); idx++) {
        if(answer > n)
            break;
        
        answer += numbers[idx];
    }
    return answer;
}