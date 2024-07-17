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
            int menu = Integer.parseInt(st.nextToken());
            switch (menu) {
                case 1 -> {
                    int num = Integer.parseInt(st.nextToken());
                    que.addFirst(num);
                    continue;
                }
                case 2 -> {
                    int num = Integer.parseInt(st.nextToken());
                    que.addLast(num);
                    continue;
                }
                case 3 -> {
                    sb.append(que.isEmpty() ? -1 : que.pollFirst());
                }
                case 4 -> {
                    sb.append(que.isEmpty() ? -1 : que.pollLast());
                }
                case 5 -> {
                    sb.append(que.size());
                }
                case 6 -> {
                    sb.append(que.isEmpty() ? 1 : 0);
                }
                case 7 -> {
                    sb.append(que.isEmpty() ? -1 : que.getFirst());
                }
                case 8 -> {
                    sb.append(que.isEmpty() ? -1 : que.getLast());
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}