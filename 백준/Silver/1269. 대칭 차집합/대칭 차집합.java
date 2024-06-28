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
        Map<Integer, Integer> map = new HashMap<>();

        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (map.containsKey(num)) {
                    map.remove(num);
                } else map.put(num, idx);
            }
        }

        System.out.println(map.size());
    }
}

