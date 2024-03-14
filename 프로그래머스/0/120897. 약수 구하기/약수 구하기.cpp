#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n) {
    vector<int> answer;
    for(int idx = 1; idx <=n; idx++) {
        if(n % idx == 0) answer.push_back(idx);
    }
    return answer;
}