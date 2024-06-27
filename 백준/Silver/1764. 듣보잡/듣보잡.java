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

        st = new StringTokenizer(br.readLine());
        int nonHeardCount = Integer.parseInt(st.nextToken());
        int nonSeenCount = Integer.parseInt(st.nextToken());

        Map<String, Integer> doeMap = new TreeMap<>();
        Map<String, Integer> resultMap = new TreeMap<>();
        for (int idx = 0; idx < nonHeardCount; idx++) {
            String name = br.readLine();
            doeMap.put(name, idx);
        }

        int count = 0;
        for (int idx = 0; idx < nonSeenCount; idx++) {
            String name = br.readLine();
            if (doeMap.containsKey(name)) {
                resultMap.put(name, count++);
            }
        }

        sb.append(count).append("\n");
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        System.out.println(sb);
    }
}

