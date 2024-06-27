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
        int dicCount = Integer.parseInt(st.nextToken());
        int laps = Integer.parseInt(st.nextToken());

        String[] dictionary = new String[dicCount + 1];
        Map<String, Integer> dicMap = new HashMap<>();

        for (int idx = 1; idx <= dicCount; idx++) {
            String name = br.readLine();
            dictionary[idx] = name;
            dicMap.put(name, idx);
        }

        for (int idx = 1; idx <= laps; idx++) {
            String input = br.readLine();

            try {
                int num = Integer.parseInt(input);
                sb.append(dictionary[num]).append("\n");
            } catch (NumberFormatException e) {
                if (dicMap.containsKey(input)) {
                    sb.append(dicMap.get(input)).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}

