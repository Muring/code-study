import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        int isAlcohol = Integer.parseInt(st.nextToken());

        int answer = 0;
        // 술하고 같이 초밥을 먹거나 점심 식사가 아닐 때는 280
        if (isAlcohol == 1) answer = 280;
        else if (time < 12 || 16 < time) answer = 280;
            // 점심 식사이면서 술과 같이 먹지 않을때는 320
        else if (isAlcohol == 0) answer = 320;

        System.out.println(answer);
    }
}