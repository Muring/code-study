#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    
    // 내림차순으로 정렬
    sort(citations.begin(), citations.end(), greater<int>());
    
    // 앞에서부터 현 인덱스의 값이 인용된 숫자보다 큰지 확인한다.
    for(int i = 0; i < citations.size(); i++){
        if(citations[i] > i)
            answer++;
        // 작아지는 순간 더 이상 확인할 필요가 없다.
        else
            break;
    }
    
    return answer;
}