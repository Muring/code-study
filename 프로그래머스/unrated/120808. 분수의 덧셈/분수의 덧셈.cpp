#include <string>
#include <vector>
#include <numeric>

using namespace std;

vector<int> solution(int numer1, int denom1, int numer2, int denom2) {
    vector<int> answer;
    int numer_sum = numer1 * denom2 + numer2 * denom1;
    int denom_sum = denom1 * denom2;
    int common_factor = gcd(numer_sum, denom_sum);
    answer.push_back(numer_sum / common_factor);
    answer.push_back(denom_sum / common_factor);

    return answer;
}