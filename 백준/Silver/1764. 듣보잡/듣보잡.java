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

        Map<String, Integer> doeMap = new TreeMap<>();  // 듣도보도 못한 사람 저장 맵
        Map<String, Integer> resultMap = new TreeMap<>();   // 듣보잡 저장 맵

        // 듣도보도 못한 사람 정보 저장
        for (int idx = 0; idx < nonHeardCount; idx++) {
            String name = br.readLine();
            doeMap.put(name, idx);
        }

        // 듣보잡 인원을 TreeMap에 넣어 자동으로 이름 사전순으로 정렬한다.
        int count = 0;  // 명 수
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

