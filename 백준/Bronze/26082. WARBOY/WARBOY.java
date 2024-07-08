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
        int competePrice = Integer.parseInt(st.nextToken());
        int competePerformance = Integer.parseInt(st.nextToken());
        int warboyPrice = Integer.parseInt(st.nextToken());

        int warboyPerformanceByPrice = competePerformance / competePrice * 3;
        int warboyPerformance = warboyPerformanceByPrice * warboyPrice;
        System.out.println(warboyPerformance);
    }
}