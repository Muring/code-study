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

        int logCount = Integer.parseInt(br.readLine());
        Map<String, Integer> companyLogMap = new TreeMap<>(Collections.reverseOrder());

        for (int idx = 0; idx < logCount; idx++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int status = st.nextToken().equals("enter") ? 1 : 0;

            // 현재 입력 받은 사람이 회사에 존재하는지 확인
            if (companyLogMap.containsKey(name)) {
                // 회사에 있던 사람이 나가는 사람이라면
                if (companyLogMap.get(name) == 1 && status == 0)
                    companyLogMap.replace(name, 0);
                else if (status == 1) {
                    companyLogMap.replace(name, 1);
                }
            } else
                companyLogMap.put(name, status);
        }

        for (Map.Entry<String, Integer> entry : companyLogMap.entrySet()) {
            if (entry.getValue() == 1)
                sb.append(entry.getKey()).append("\n");
        }
        System.out.println(sb);
    }
}

