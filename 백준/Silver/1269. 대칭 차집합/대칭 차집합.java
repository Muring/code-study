import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 범위 받을 필요 없으니 제거
        br.readLine();
        Set<Integer> set = new HashSet<>();

        // 중복된 값이 발생할 경우 해당 값 제거
        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (set.contains(num)) {
                    set.remove(num);
                } else set.add(num);
            }
        }

        // 남아있는 사이즈가 답이다.
        System.out.println(set.size());
    }
}

