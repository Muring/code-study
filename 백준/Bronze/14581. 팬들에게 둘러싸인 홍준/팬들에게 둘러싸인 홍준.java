import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        System.out.printf("""
                :fan::fan::fan:
                :fan::%s::fan:
                :fan::fan::fan:
                """, name);
    }
}