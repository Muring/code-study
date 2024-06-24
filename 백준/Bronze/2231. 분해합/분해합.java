import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 숫자 입력 및 길이 얻기
        String inputNum = br.readLine().trim();
        int numLen = inputNum.length();

        // 입력 받은 수 정수 변환
        int num = Integer.parseInt(inputNum);
        int constNum = 0;

        for (int idx = 0; idx < num; idx++) {
            int tempNum = idx;
            int sum = 0;    // 각 자릿수의 합

            while (tempNum != 0) {
                sum += tempNum % 10;    // 각 자릿수 뽑아내기
                tempNum /= 10;
            }

            // 생성자 + 생성자 각 자릿수의 합 == 입력받은 수 일 때
            if (sum + idx == num) {
                constNum = idx;
                break;
            }
        }

        // 결과 출력
        System.out.println(constNum);
    }
}
