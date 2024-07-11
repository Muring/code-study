import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // 데이터 배열
        String[][] dataArray = {
                {"Algorithm", "204"},
                {"DataAnalysis", "207"},
                {"ArtificialIntelligence", "302"},
                {"CyberSecurity", "B101"},
                {"Network", "303"},
                {"Startup", "501"},
                {"TestStrategy", "105"}
        };

        // Map 선언
        Map<String, String> classMap = new HashMap<>();

        // 반복문을 통해 배열의 데이터를 Map에 삽입
        for (String[] data : dataArray) {
            classMap.put(data[0], data[1]);
        }


        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            String input = br.readLine();
            sb.append(classMap.get(input)).append("\n");
        }
        System.out.println(sb);
    }
}