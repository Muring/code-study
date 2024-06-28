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

        br.readLine();
        Set<Integer> set = new HashSet<>();

        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (set.contains(num)) {
                    set.remove(num);
                } else set.add(num);
            }
        }

        System.out.println(set.size());
    }
}

