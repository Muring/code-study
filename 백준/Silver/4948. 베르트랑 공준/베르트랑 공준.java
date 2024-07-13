import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) break;  // 종료 조건
            int primeCount = 0; // 소수 개수 저장 변수

            // 입력받은 수부터 *2의 범위까지 반복
            for (int num = input + 1; num <= input * 2; num++) {
                boolean isPrime = true; // 소수인지 확인하는 변수
                // 각 수가 소수인지 아닌지 확인
                for (int jdx = 2; jdx <= Math.sqrt(num); jdx++) {
                    if (num % jdx == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime == true) primeCount++;
            }

            sb.append(primeCount).append("\n");

        }
        System.out.println(sb);
    }
}