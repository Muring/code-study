#include <string>
#include <vector>
#include <cmath>
#include <limits.h>
#include <algorithm>

using namespace std;

int solution(vector<int> array, int n) {
    int answer = INT_MAX;
    sort(array.begin(), array.end());
    for(int idx = array.size() - 1; idx >= 0; idx--) {
        if(abs(array[idx] - n) <= abs(answer)) 
            answer = array[idx] - n;
    }
    return answer + n;
}