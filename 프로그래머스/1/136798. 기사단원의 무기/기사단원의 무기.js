function solution(number, limit, power) {
    function getDivisor(num) {
        let divisors = [];
        
        for(let idx = 0; idx <= Math.sqrt(num); idx++) {
            if(num % idx === 0) {
                divisors.push(idx);
                if(idx !== num / idx) {
                    divisors.push(num / idx);
                }
            }
        }
        return divisors.length;
    }
    
    var attackArr = [];
    for(let idx = 1; idx <= number; idx++) {
        var attack = getDivisor(idx);
        if(attack > limit) {
            attack = power;
        }
        
        attackArr.push(attack);
    }

    return attackArr.reduce((accumulater, currentValue) => accumulater + currentValue, 0);
}