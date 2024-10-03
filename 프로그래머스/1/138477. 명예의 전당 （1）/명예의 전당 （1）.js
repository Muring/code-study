function solution(k, score) {
    var answer = [];
    
    var scoreArr = [];
    for(let num of score) {
        scoreArr.push(num);
        scoreArr.sort((a, b) => b - a);
        
        if(scoreArr.length <= k - 1) {
            answer.push(scoreArr[scoreArr.length - 1]);
        } else {
            answer.push(scoreArr[k - 1]);
        }
    }
    
    return answer;
}