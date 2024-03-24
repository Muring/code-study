#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <math.h>

using namespace std;

set<int> answer;

bool CheckPrime(int num) {
    if(num <= 1) return 0;
    else {
        for(int idx = 2; idx <= num / 2; idx++) {
            if(num % idx == 0) return 0;
        }
    }
    
    return 1;
}

int solution(string numbers) {

    // next_permutation을 사용하기 위해 오름차순 정렬
    sort(numbers.begin(), numbers.end());

    // 처음 정렬된 값을 지나치지 않기 위해 do-while 사용
    do {
        for(int idx = 0; idx < numbers.size(); idx++) {
            if(CheckPrime(stoi(numbers.substr(0, idx + 1))))
                answer.insert(stoi(numbers.substr(0, idx + 1)));
        }
    } while (next_permutation(numbers.begin(), numbers.end()));
    
    
    return answer.size();
}