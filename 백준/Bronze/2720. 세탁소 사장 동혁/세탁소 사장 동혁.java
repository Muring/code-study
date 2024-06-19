import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static final int[] coinList = {25, 10, 5, 1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for(int idx = 0; idx < laps; idx++) {
            int[] answer = new int[4];
            int leftover = Integer.parseInt(br.readLine());
            for(int coinIdx = 0; coinIdx < coinList.length; coinIdx++) {
                if(leftover / coinList[coinIdx] > 0) {
                    answer[coinIdx] = leftover / coinList[coinIdx];
                    leftover %= coinList[coinIdx];
                }
                sb.append(answer[coinIdx]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
