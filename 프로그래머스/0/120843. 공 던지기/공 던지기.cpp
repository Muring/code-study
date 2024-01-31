#include <string>
#include <vector>

using namespace std;
/*
// numbers의 길이가 짝수인 경우 numbers의 길이 / 2를 한 값을 
// k-1로 나누고 남은 나머지만큼만 이동시키면 된다.
// numbers의 길이가 홀수인 경우 numbers의 길이만큼이 회전 주기이다.
*/ 
int solution(vector<int> numbers, int k) {
    int answer = 0;
    int numbers_idx = 0;
    for(int idx = 0; idx < k - 1; idx++) {
        if(numbers_idx + 2 > numbers.size()) {
            numbers_idx -= (numbers.size() - 2);
            continue;
        }
        numbers_idx += 2;
        
    }
    return numbers[numbers_idx];
}

// 1 2 3 4 5
// 출발 1
// k=1 3
// k=2 5
// k=3 2
// k=4 4
// k=5 1
