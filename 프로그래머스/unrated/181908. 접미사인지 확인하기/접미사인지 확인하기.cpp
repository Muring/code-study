#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(string my_string, string is_suffix) {
	int answer = 0;
	int length = my_string.length();
	for (int len = 0; len < length; len++) {
		string temp = "";
		for (int idx = length - 1 - len; idx < length; idx++) {
			temp += my_string[idx];
		}
		cout << temp << endl;
		if (temp.compare(is_suffix) == 0) {
			answer = 1;
			break;
		}
	}
	cout << answer;
	return answer;
}