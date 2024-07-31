import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static final int LENGTH = 8;

    private static String check(int start) {
        // start가 1이면 오름차순이여야 한다.
        if (start == 1) {
            for (int idx = 1; idx < LENGTH; idx++) {
                int num = Integer.parseInt(st.nextToken());
                if (++start != num)
                    return "mixed";
            }
            return "ascending";
        }
        // start가 8이면 내림차순이어야 한다.
        else if (start == 8) {
            for (int idx = 1; idx < LENGTH; idx++) {
                int num = Integer.parseInt(st.nextToken());
                if (--start != num)
                    return "mixed";
            }
            return "descending";
        }
        // 모두 해당되지 않으면 mixed이다.
        return "mixed";
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        System.out.println(check(num));
    }
}