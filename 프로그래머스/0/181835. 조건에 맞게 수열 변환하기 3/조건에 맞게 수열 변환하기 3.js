function solution(arr, k) {
    
    if(k % 2 == 0) {
        for(let idx = 0; idx < arr.length; idx++) {
            arr[idx] += k;
        }
    } else {
        for(let idx = 0; idx < arr.length; idx++) {
            arr[idx] *= k;
        }
    }
    
    return arr;
}