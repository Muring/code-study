import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static final int IV = 10000000; // 10,000,000 상수 독립 변수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int[] numArr = new int[2 * IV + 1]; // 음수까지 포함하는 배열

        int myCardCount = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < myCardCount; idx++) {
            int num = Integer.parseInt(st.nextToken());
            numArr[num + IV]++;
        }

        int laps = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(numArr[num + IV]).append(" ");
        }

        System.out.println(sb);
    }
}

