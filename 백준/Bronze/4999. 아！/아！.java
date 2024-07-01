import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String me = br.readLine();
        String doc = br.readLine();
        if (me.equals(doc)) System.out.println("go");
        else if (me.length() > doc.length()) System.out.println("go");
        else System.out.println("no");
    }
}
