import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    static List<String> strList;
    static int num;

    private static String printLine(int idx) {
        return printStar(idx) + printSpace((num - idx) * 2) + printStar(idx);
    }

    private static String printStar(int len) {
        return "*".repeat(len);
    }

    private static String printSpace(int len) {
        return " ".repeat(len);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        num = Integer.parseInt(br.readLine());
        strList = new ArrayList<>();

        for (int idx = 1; idx <= num; idx++) {
            strList.add(printLine(idx));
        }

        boolean flag = false;
        int idx = -1;
        while (true) {
            if (!flag) idx++;
            else idx--;

            if (flag && idx == -1) break;

            sb.append(strList.get(idx)).append("\n");

            if (idx == num - 1) flag = true;
        }
        System.out.println(sb);
    }
}