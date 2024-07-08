import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        sb.append(("""
                 @@@   @@@\s
                @   @ @   @
                @    @    @
                @         @
                 @       @\s
                  @     @ \s
                   @   @  \s
                    @ @   \s
                     @    \s
                """).repeat(laps));
        System.out.println(sb);
    }
}