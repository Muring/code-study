import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static void cantor(int start, int size) {
        if (size == 1) return;

        int currentSize = size / 3;
        for (int idx = start + currentSize; idx < start + currentSize * 2; idx++) {
            sb.setCharAt(idx, ' ');
        }

        cantor(start, currentSize); // 3등분한 문자열의 앞 부분
        cantor(start + currentSize * 2, currentSize); // 뒷 부분
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {
            // 초기화
            sb.setLength(0);

            // 초기 설정
            int num = Integer.parseInt(input);
            int len = (int) Math.pow(3, num);
            for (int idx = 0; idx < len; idx++)
                sb.append("-");

            // 칸토어 실행
            cantor(0, len);
            System.out.println(sb);
        }
    }
}