fun main() {
    val limit = 5;
    val numbers = IntArray(limit);

    var num = 0;
    for(idx in 0 .. 4) {
        num = readln().toInt();
        numbers[idx] = num;
    }

    // 평균 구하기
    val sum = numbers.sum();
    val avg = sum / limit;

    // 배열 정렬하기
    numbers.sort();

    println(avg);
    println(numbers[limit / 2]);
}