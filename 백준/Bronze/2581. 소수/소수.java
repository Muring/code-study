import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static final int MAX_VALUE = Integer.MAX_VALUE;

    private static boolean isPrime(int num) {
        if(num == 1) return false;

        int count = 0;
        for(int idx = 2; idx < num; idx++) {
            if(num % idx == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());
        int sum = 0;
        int minPrime = MAX_VALUE;

        for(int idx = start; idx <= end; idx++) {
            // 소수 찾기
            if(isPrime(idx)) {
                // 소수의 합 구하기
                sum += idx;
                // 최솟값 구분하기
                minPrime = Math.min(minPrime, idx);
            }
        }
        if(sum != 0 && minPrime < MAX_VALUE) {
            sb.append(sum).append("\n").append(minPrime);
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }

    }
}
