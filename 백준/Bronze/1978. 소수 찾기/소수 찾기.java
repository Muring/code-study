import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

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
        int total = Integer.parseInt(br.readLine());
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < total; idx++) {
            int num = Integer.parseInt(st.nextToken());

            // 소수인지 확인
            if(isPrime(num)) answer++;
        }
        System.out.println(answer);
    }
}
