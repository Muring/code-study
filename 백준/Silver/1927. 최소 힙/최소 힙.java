import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    static PriorityQueue<Integer> que;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 초기화
        que = new PriorityQueue<>();
        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            // 입력
            int num = Integer.parseInt(br.readLine());
            int answer = 0;
            if (num == 0 && !que.isEmpty()) {
                answer = que.poll();
                sb.append(answer).append("\n");
            } else if (num != 0) que.offer(num);
            else sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}