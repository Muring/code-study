import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int num1, num2;

        while(true) {
            st = new StringTokenizer(br.readLine());
            num1 = Integer.parseInt(st.nextToken());
            num2 = Integer.parseInt(st.nextToken());

            if(num1 == 0 && num2 == 0) break;

            if(num2 % num1 == 0)
                System.out.println("factor");
            else if(num1 % num2 == 0)
                System.out.println("multiple");
            else System.out.println("neither");
        }
    }
}
