import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    private static List<Integer> getDivisors(int num) {
        List<Integer> divisors = new ArrayList<>();

        for(int idx = 1; idx < num; idx++) {
            if(num % idx == 0)
                divisors.add(idx);
        }
        return divisors;
    }

    private static boolean isPerfect(List<Integer> divisors, int num) {
        int sum = 0;
        for(int divisor : divisors)
            sum += divisor;

        return sum == num;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int num = 0;

        while(true) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());

            // 기저조건
            if(num == -1) break;

            // 약수 구하기
            List<Integer> divisors = getDivisors(num);

            // 완전수 확인
            if(isPerfect(divisors, num)) {
                sb.append(num).append(" = ");
                for(int idx = 0; idx < divisors.size(); idx++){
                    sb.append(divisors.get(idx));
                    if(idx + 1 != divisors.size())
                        sb.append(" + ");
                }
            }
            else {
                sb.append(num).append(" is NOT perfect.");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
