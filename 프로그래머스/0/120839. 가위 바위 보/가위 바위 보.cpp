#include <math.h>
#include <string>
#include <vector>

using namespace std;

using namespace std;

string solution(string rsp) {
    string answer = "";
    string sheet[6] = {"5", "0", "0", "0", "0", "2"};

    for (int idx = 0; idx < rsp.size(); idx++) {
        int temp_idx = rsp[idx] - '0';
        answer += sheet[temp_idx];
    }
    return answer;
}