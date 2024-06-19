import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int answer = 1;
        int strLen = str.length();
        for(int idx = 0; idx < strLen / 2; idx++) {
            if(str.charAt(idx) != str.charAt(strLen - idx - 1)){
                answer = 0;
                break;
            }
        }
        System.out.println(answer);
    }
}
