#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n) {
    vector<int> answer;
    vector<bool> used_prime(n + 1, false); 
    
    for(int idx = 2; idx <= n; idx++) {
        if(n % idx == 0) {
            if(used_prime[idx] == false) {
                used_prime[idx] = true;
                answer.push_back(idx);
            }
            n /= idx;
            idx--;
        }
    }
    
    return answer;
}