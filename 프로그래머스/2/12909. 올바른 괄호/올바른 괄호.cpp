#include<string>
#include <iostream>
#include <stack>
using namespace std;

bool solution(string s)
{
    bool answer = true;
    stack<char> stk;
    
    for(int i = 0; i < s.size(); i++){
        if(s[i] == '(')
            stk.push('(');
        else{
            if(!stk.empty() && stk.top() == '(')
                stk.pop();
            else
                stk.push(')');
        }
    }
    if(stk.empty() == true)
        answer = true;
    else
        answer = false;
        
    return answer;
}