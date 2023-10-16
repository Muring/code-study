#include <string>
#include <vector>

using namespace std;

vector<int> solution(int l, int r) {
    vector<int> answer;
    for(int idx = l; idx <= r; idx++){
        bool check = true;
        if(idx % 5 == 0){
            int temp = idx;
            while(temp > 0){
                int num = temp % 10;
                if(num != 0 && num != 5){
                    check = false;
                    break;
                }
                temp /= 10;
            }
            if(check)
                answer.push_back(idx);
        }
    }
    if(answer.size() == 0)
        answer.push_back(-1);
    return answer;
}