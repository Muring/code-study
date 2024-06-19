import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static Map<Integer, Character> systemMap;

    static {
        systemMap = new HashMap<>();
        for(int idx =0;idx <10;idx++)
            systemMap.put(idx, (char) (idx + '0'));
        for (int idx =10;idx <36;idx++) {
            systemMap.put(idx, (char) (idx - 10 + 'A'));
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int system = Integer.parseInt(st.nextToken());
        // 10 5 2+1 1 0
        while(num > 0) {
            sb.insert(0, systemMap.get(num % system));
            num /= system;
        }
        System.out.println(sb);
    }
}
