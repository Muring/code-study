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

        int num = Integer.parseInt(st.nextToken());
        int answerIdx = Integer.parseInt(st.nextToken());
        int divisorCnt = 0;

        for(int idx = 1; idx <= num; idx++) {
            if(num % idx == 0)
                divisorCnt++;

            if(divisorCnt == answerIdx){
                System.out.println(idx);
                break;
            }
        }
        if(divisorCnt < answerIdx)
            System.out.println(0);
    }
}
