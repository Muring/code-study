#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> intStrs, int k, int s, int l) {
    vector<int> answer;
    for(string str : intStrs){
        int num = 0;
        for(int idx = s; idx < s + l; idx++){
            num = num * 10 + (str[idx] - '0');
        }
       if(num > k)
           answer.push_back(num);
        
    }
    return answer;
}