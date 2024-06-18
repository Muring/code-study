
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		int total = Integer.parseInt(st.nextToken());
		int lap = Integer.parseInt(st.nextToken());

		int[] basketList = new int[total];
		for (int idx = 0; idx < lap; idx++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int inputNum = Integer.parseInt(st.nextToken());

			for (int jdx = start; jdx <= end; jdx++) {
				basketList[jdx - 1] = inputNum;
			}
		}

		for (int num : basketList)
			System.out.print(num + " ");
	}
}