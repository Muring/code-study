#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    
    // 반복되는 규칙
    vector<int> user1 = { 1, 2, 3, 4, 5 };
    vector<int> user2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
    vector<int> user3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
    
    // 정답 배열 세 개 생성
    vector<int> correct(3, 0);
    // 각각 반복하며 정답일 경우 정답 세기
    for(int idx = 0; idx < answers.size(); idx++) {
        if(user1[idx % user1.size()] == answers[idx]) correct[0]++;
        if(user2[idx % user2.size()] == answers[idx]) correct[1]++;
        if(user3[idx % user3.size()] == answers[idx]) correct[2]++;
    }
    
    // 가장 많은 문제를 맞춘 사람 구하기
    int biggest = 0;
    for(int idx = 0; idx < correct.size(); idx++) {
		biggest = max(biggest, correct[idx]);   
    }
    
    // 만약 가장 많은 문제를 맞춘 사람이 여러명일 경우의 예외처리
    for(int idx = 0; idx < correct.size(); idx++) {
        if(correct[idx] == biggest)
            answer.push_back(idx + 1);
    }
    
    return answer;
}