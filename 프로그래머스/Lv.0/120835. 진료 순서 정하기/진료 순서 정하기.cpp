#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> emergency) {
    vector<int> answer = emergency;
    vector<int> temp = emergency;
    sort(temp.begin(), temp.end());
    
    int rank = 1;
    for(int idx = temp.size() - 1; idx >= 0; idx--) {
        for(int i = 0; i < emergency.size(); i++) {
            if(temp[idx] == emergency[i]) {
                answer[i] = rank++; 
            }
        }
        
    }
    return answer;
}