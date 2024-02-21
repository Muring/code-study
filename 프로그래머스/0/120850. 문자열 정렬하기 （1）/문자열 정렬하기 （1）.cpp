#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(string my_string) {
    vector<int> answer;
    for(int x : my_string) {
        if(x - '0' >= 0 && x - '0' < 10)
            answer.push_back(x - '0');
    }
    sort(answer.begin(), answer.end());
    
    return answer;
}