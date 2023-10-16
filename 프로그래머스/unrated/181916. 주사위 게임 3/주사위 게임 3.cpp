#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int solution(int a, int b, int c, int d) {
    int answer = 0;
    map<int, int> counts;
    counts[a]++;
    counts[b]++;
    counts[c]++;
    counts[d]++;
    
    for(auto it : counts) {
        if(it.second == 4)
            answer = max(answer, 1111 * it.first);
        else if(it.second == 3) {
            for(auto it2 : counts) {
                if(it2.second == 1)
                    answer = max(answer, (10 * it.first + it2.first) * (10 * it.first + it2.first));
            }
        }
        else if(it.second == 2){
            int second_two = 0;
            for(auto it2 : counts) {
                if(it2.second == 2 && it2.first != it.first) {
                    second_two = it2.first;
                    answer = max(answer, (it.first + second_two) * abs(it.first - second_two));
                }
            }
            if(second_two == 0) {
                vector<int> others;
                for(auto it2 : counts) {
                    if(it2.second == 1)
                        others.push_back(it2.first);
                }
                answer = max(answer, others[0] * others[1]);
            }
            
        }
    }
    
    if(answer == 0)
        answer = min({a, b, c, d});
    return answer;
}