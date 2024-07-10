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
        univ.put("M", "MatKor");
        univ.put("W", "WiCys");
        univ.put("C", "CyKor");
        univ.put("A", "AlKor");
        univ.put("$", "$clear");
        String input = br.readLine();
        System.out.println(univ.get(input));
    }
}