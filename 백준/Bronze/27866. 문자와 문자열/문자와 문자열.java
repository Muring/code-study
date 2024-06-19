import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().trim();
		int idx = Integer.parseInt(br.readLine());
		System.out.println(str.charAt(idx - 1));
	}
}
