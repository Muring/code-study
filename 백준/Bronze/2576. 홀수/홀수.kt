fun main() {
    var minNum = 100;
    var sum = 0;
    for(idx in 1 .. 7) {
        val inputNum = readln().toInt();
        if(inputNum % 2 != 0) {
            minNum = minNum.coerceAtMost(inputNum);
            sum += inputNum;
        }
    }
    if(sum != 0) {
        println(sum);
        print(minNum);
    } else print("-1");

}