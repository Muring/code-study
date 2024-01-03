#include <string>
#include <vector>

using namespace std;

vector<int> solution(int start, int end_num) {
    vector<int> answer;
    int range = start - end_num;
    for(int idx = 0; idx <= range; idx++){
        answer.push_back(start - idx);
    }
    return answer;
}