#include <string>
#include <vector>
#include <cmath>

using namespace std;

int solution(int a, int b, int c) {
    int answer = 0;
    int sum = a + b + c;
    int sum_square = a*a + b*b + c*c;
    int sum_triple_square = pow(a, 3) + pow(b, 3) + pow(c, 3);
    if(a != b && b != c && a != c)
        answer = sum;
    else if(a == b || b == c || a == c){
        if(a == b && b == c && c == a)
            answer = sum * sum_square * sum_triple_square;
        else
            answer = sum * sum_square;
    }
    return answer;
}