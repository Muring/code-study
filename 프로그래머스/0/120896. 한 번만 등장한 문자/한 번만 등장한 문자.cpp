#include <string>
#include <vector>

using namespace std;

string solution(string s) {
    string answer = "";
    vector<int> is_one(26, 0);
    for(auto x : s) {
        is_one[x - 'a']++; 
    }
    for(int idx = 0; idx < is_one.size(); idx++) {
        if(is_one[idx] == 1) answer += idx + 'a';
    }
    
    return answer;
}