import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 3009 네 번째 점
 * 각 열에서 숫자를 셌을 때, 개수가 홀 수인 수를 반환하면 된다.
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for(int idx = 0; idx < 3; idx++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(xList.contains(x)) {
                xList.remove(Integer.valueOf(x));
            } else {
                xList.add(x);
            }

            if(yList.contains(y)){
                yList.remove(Integer.valueOf(y));
            }
            else {
                yList.add(y);
            }
        }
        System.out.printf("%d %d", xList.get(0), yList.get(0));
    }
}
