#include <string>
#include <vector>

using namespace std;

int count = 0;
char alphabet[5] = {'A', 'E', 'I', 'O', 'U'};

void DFS(string word, string str, int *answer) {
    if(word == str) {
        *answer = count;
        return;
    }
    
    for(int idx = 0; idx < 5; idx++) {
        if(str.size() < 5) {
            count++;
            DFS(word, str + alphabet[idx], answer);
        }
    }
}

int solution(string word) {
    int answer = 0;
    // answer를 포인터로 직접 변경
    DFS(word, "", &answer);
    return answer;
}