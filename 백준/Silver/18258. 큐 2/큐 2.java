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

        Deque<Integer> que = new LinkedList<>();
        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            st = new StringTokenizer(br.readLine());

            String order = st.nextToken();
            switch (order) {
                case "push" -> {
                    que.offer(Integer.parseInt(st.nextToken()));
                    continue;
                }
                case "pop" -> {
                    sb.append(que.isEmpty() ? -1 : que.poll());
                }
                case "size" -> {
                    sb.append(que.size());
                }
                case "empty" -> {
                    sb.append(que.isEmpty() ? 1 : 0);
                }
                case "front" -> {
                    sb.append(que.isEmpty() ? -1 : que.peek());
                }
                case "back" -> {
                    sb.append(que.isEmpty() ? -1 : que.getLast());
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}