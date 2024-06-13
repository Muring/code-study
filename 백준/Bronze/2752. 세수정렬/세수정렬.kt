fun main() {
    val numString = readln();
    // 문자열을 공백을 기준으로 분리하여 리스트로 변환
    val numList = numString.split(" ");
    // 리스트의 각 요소를 정수로 변환한 후 배열에 저장
    val numArray = numList.map { it.toInt() }.toIntArray();
    // 배열 정렬
    numArray.sort();
    print(numArray.joinToString(" "));
}