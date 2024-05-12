#include <vector>
#include <iostream>
#include <stack>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    stack<int> stack;
    
    for(int num : arr) {
        if(stack.empty()) {
            stack.push(num);
        }
        
        if(stack.top() != num) {
            answer.push_back(stack.top());
            stack.pop();
            stack.push(num);
        }
    }
    answer.push_back(arr[arr.size() - 1]);
    
    return answer;
}