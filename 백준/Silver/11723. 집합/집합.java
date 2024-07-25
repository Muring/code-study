import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            st = new StringTokenizer(br.readLine());
            String menu = st.nextToken();
            int num = 0;
            switch (menu) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    if (!list.contains(num)) list.add(num);
                    break;

                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    if (list.contains(num)) {
                        for (int jdx = 0; jdx < list.size(); jdx++) {
                            if (list.get(jdx) == num) list.remove(jdx);
                        }
                    }
                    break;

                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append(list.contains(num) ? 1 : 0).append("\n");
                    break;

                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    if (list.contains(num)) {
                        for (int jdx = 0; jdx < list.size(); jdx++) {
                            if (list.get(jdx) == num) list.remove(jdx);
                        }
                    } else list.add(num);
                    break;

                case "all":
                    list.clear();
                    for (int jdx = 1; jdx <= 20; jdx++)
                        list.add(jdx);
                    break;

                case "empty":
                    list.clear();
                    break;
            }
        }
        System.out.println(sb);
    }
}