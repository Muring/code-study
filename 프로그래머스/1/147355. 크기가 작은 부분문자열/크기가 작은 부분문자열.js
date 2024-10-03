function solution(t, p) {
    var answer = 0;
    
    const arr = [];
    for(let idx = 0; idx <= t.length - p.length; idx++) {
        arr.push(t.slice(idx, idx + p.length));
    }
    
    for(let num of arr) {
            console.log(num);
        if(num <= p) {
            answer++;
        }
    }
    
    return answer;
}