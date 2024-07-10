import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> univ = new HashMap<>();
        univ.put("SONGDO", "HIGHSCHOOL");
        univ.put("CODE", "MASTER");
        univ.put("2023", "0611");
        univ.put("ALGORITHM", "CONTEST");
        String input = br.readLine();
        System.out.println(univ.get(input));
    }
}