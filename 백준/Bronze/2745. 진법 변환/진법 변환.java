import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static Map<Character, Integer> systemMap;

    static {
        systemMap = new HashMap<>();
        for(char ch = '0'; ch <= '9'; ch++)
            systemMap.put(ch, (ch - '0'));
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            systemMap.put(ch, 10 + (ch - 'A'));
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();    // 입력받은 수
        int system = Integer.parseInt(st.nextToken()); // 진수
        int sum = 0;

        int strLen = str.length();
        for(int idx = 0; idx < strLen; idx++) {
            char ch = str.charAt(strLen -1 - idx);
            sum += (int) (systemMap.get(ch) * Math.pow(system, idx));
        }
        System.out.println(sum);
    }
}
