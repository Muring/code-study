import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br;

    private static int gcd(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);

        return gcd.intValue();
    }

    private static int gcd(int[] arr) {
        int result = arr[0];
        for (int idx = 1; idx < arr.length; idx++) {
            result = gcd(result, arr[idx]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // input
        int treeCount = Integer.parseInt(br.readLine());
        int[] trees = new int[treeCount];
        for (int idx = 0; idx < treeCount; idx++) {
            trees[idx] = Integer.parseInt(br.readLine());
        }

        // 현재 가로수 위치의 차를 모두 저장
        int[] gaps = new int[treeCount - 1];
        for (int idx = 0; idx < treeCount - 1; idx++) {
            gaps[idx] = trees[idx + 1] - trees[idx];
        }

        // 모든 차의 최대 공약수 구하기
        int gcd = gcd(gaps);

        // 나무 갯수 구하기
        int answer = 0;
        for (int gap : gaps) {
            answer += gap / gcd - 1;
        }

        System.out.println(answer);
    }
}