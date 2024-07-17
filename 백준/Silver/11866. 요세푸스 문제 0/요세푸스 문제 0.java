import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력
        st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());
        int deleteIdx = Integer.parseInt(st.nextToken());

        // 큐 초기화
        // 맨 앞, 뒤에 전부 접근하기 위해 Deque사용
        Deque<Integer> que = new LinkedList<>();
        for (int idx = 1; idx <= count; idx++)
            que.offer(idx);

        // 큐의 숫자 제거
        sb.append("<");
        while (!que.isEmpty()) {
            for (int idx = 1; idx <= deleteIdx; idx++) {
                if (idx == deleteIdx) sb.append(que.poll());
                else que.offerLast(que.poll());
            }
            if (!que.isEmpty()) sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb);
    }
}