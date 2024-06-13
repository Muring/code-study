fun main() {

    var answer = ' ';
    for(idx in 1 .. 3) {
        var zeroCnt = 0;
        val yoot = readln();
        val yootToIntList = yoot.split(" ");
        for(num in yootToIntList) {
            if(num.toInt() == 0) zeroCnt++;
        }
        answer = when(zeroCnt) {
            1 -> 'A';
            2 -> 'B';
            3 -> 'C';
            4 -> 'D';
            else -> 'E';
        }
        println(answer);
    }
}