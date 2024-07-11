import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        final String[] phrases = {"Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around and desert you",
                "Never gonna make you cry",
                "Never gonna say goodbye",
                "Never gonna tell a lie and hurt you",
                "Never gonna stop"};
        List<String> phraseList = new ArrayList<>(Arrays.asList(phrases));
        int laps = Integer.parseInt(br.readLine());
        String answer = "No";
        for (int idx = 0; idx < laps; idx++) {
            String phrase = br.readLine();
            if (!phraseList.contains(phrase)) {
                answer = "Yes";
                break;
            }
        }
        System.out.println(answer);
    }
}