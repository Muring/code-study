#include <string>
#include <vector>
#include <cstring>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    // 모든 알파벳의 사용 여부를 확인할 수 있는 배열을 하나 생성한다.
	 const int ALPHABET_COUNT = 26;
	 int alpha_found[ALPHABET_COUNT][2];
	 memset(alpha_found, 0, sizeof(alpha_found));

	 for (int idx = 0; idx < s.length(); idx++) {
		 // 검색하는 알파벳의 alpha_found 인덱스 찾기
		 int alpha_idx = s[idx] - 97;
		 // 알파벳이 사용된 적이 있다면
		 if (alpha_found[alpha_idx][0] == 1) {
			 // 저장된 가장 최근의 해당 알파벳 위치를 정답 벡터에 저장
			 answer.push_back(idx - alpha_found[alpha_idx][1]);
			 // 이후 가장 최근의 알파벳 위치 업데이트
			 alpha_found[alpha_idx][1] = idx;
		 }
		 // 알파벳이 사용된 적이 없다면
		 else {
			 answer.push_back(-1);
			 alpha_found[alpha_idx][0] = 1;
			 alpha_found[alpha_idx][1] = idx;
		 }
	}
    return answer;
}