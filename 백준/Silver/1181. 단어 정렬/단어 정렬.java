import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static void Solution(int laps) throws IOException {
        // 중복 제거를 위한 Set 생성
        Set<String> uniqueString = new HashSet<>();

        // 배열에 입력
        for (int idx = 0; idx < laps; idx++) {
            uniqueString.add(br.readLine());
        }

        // 배열 생성
        List<String> uniqueStringList = new ArrayList<>(uniqueString);

        // 오름차순 정렬
        uniqueStringList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int lengthComparison = Integer.compare(s1.length(), s2.length());
                if (s1.length() != s2.length())
                    return lengthComparison;
                else return s1.compareTo(s2);
            }
        });

        // 결과 출력
        for (String str : uniqueStringList) {
            sb.append(str).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int laps = Integer.parseInt(br.readLine());

        Solution(laps);
    }
}
