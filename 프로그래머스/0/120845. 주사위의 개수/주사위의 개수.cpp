#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> box, int n) {
    int answer = 1;
    for(int idx = 0; idx < box.size(); idx++)
        answer *= box[idx] / n;
    
    return answer;
}