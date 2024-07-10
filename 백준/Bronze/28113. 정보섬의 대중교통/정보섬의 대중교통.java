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

        // 지하철까지 걸어가는 시간보다 지하철 출발까지 남은 시간이 같거나 커야 지하철을 탈 수 있다.
        // 즉, N(timeToSub)보다 B(subLeftTime)가 크거나 같으면서
        // B(subLeftTime)가 A(busLeftTime)보다 작으면 Subway, 크면 Bus, 같으면 Anything
        int timeToSub = Integer.parseInt(st.nextToken());
        int busLeftTime = Integer.parseInt(st.nextToken());
        int subLeftTime = Integer.parseInt(st.nextToken());

        String answer = "";
        if (busLeftTime - timeToSub < subLeftTime - timeToSub) answer = "Bus";
        else if (busLeftTime - timeToSub > subLeftTime - timeToSub) answer = "Subway";
        else answer = "Anything";
        System.out.println(answer);
    }
}