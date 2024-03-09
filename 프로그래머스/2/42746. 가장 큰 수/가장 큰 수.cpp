#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b) {
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    string answer = "";     // 정답 변수
    vector<string> temp;    // 숫자 스트링형 임시 저장 변수
    
    // temp 배열에 숫자를 전부 string으로 변환 후 저장
    for(int num : numbers)
        temp.push_back(to_string(num));
    
    // 두 숫자를 더했을 때 더 큰 수의 조합으로 정렬되도록 조건부 정렬
    sort(temp.begin(), temp.end(), compare);
    
    // 답이 0일 때의 예외문
    if(temp.at(0) == "0") return "0";
    
    // 마지막 정답 하나의 string으로 변환
    for(auto num : temp)
        answer += num;
    return answer;
}