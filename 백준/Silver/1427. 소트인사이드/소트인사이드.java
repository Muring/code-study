import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static void Solution(String input) throws IOException {
        int len = input.length();
        Integer[] arr = new Integer[len];

        // String으로 입력된 수를 쪼개어 int형으로 변환 후 저장
        for (int idx = 0; idx < len; idx++) {
            arr[idx] = input.charAt(idx) - '0';
        }

        Arrays.sort(arr, Collections.reverseOrder());

        for (int idx = 0; idx < len; idx++) {
            sb.append(arr[idx]);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String input = br.readLine();

        Solution(input);
    }
}
