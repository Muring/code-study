import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static Set<String> getSubStrings(String input) {
        Set<String> substrings = new TreeSet<>();

        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                substrings.add(input.substring(i, j));
            }
        }
        return substrings;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String input = br.readLine();
        Set<String> substrings = getSubStrings(input);
        System.out.println(substrings.size());
    }
}
