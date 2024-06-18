import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		long sum = 0;
		while (st.hasMoreElements()) {
			sum += Long.parseLong(st.nextToken());
		}
		System.out.println(sum);
	}
}
