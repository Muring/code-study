import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static void Solution(Map<String, Integer> map, int laps) throws IOException {
        int count = 0;

        for (int idx = 0; idx < laps; idx++) {
            if (map.containsKey(br.readLine().trim())) count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int baseCount = Integer.parseInt(st.nextToken());
        int checkCount = Integer.parseInt(st.nextToken());
        Map<String, Integer> baseMap = new HashMap<>();

        for (int idx = 0; idx < baseCount; idx++) {
            baseMap.put(br.readLine(), idx);
        }

        Solution(baseMap, checkCount);
    }
}

