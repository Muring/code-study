import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        int n, s;
        while (sc.hasNext()) {
            n = sc.nextInt();
            s = sc.nextInt();
            System.out.println(s / (n + 1));
        }
    }
}