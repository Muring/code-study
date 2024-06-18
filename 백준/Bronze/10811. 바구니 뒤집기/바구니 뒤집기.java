import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] basketList;

	static void basketInit(int count) {
		basketList = new int[count];
		for (int idx = 0; idx < count; idx++)
			basketList[idx] = idx + 1;
	}

	static void reverse(int start, int end) {
		int temp = 0;
		for (int idx = 0; idx <= (end - start) / 2; idx++) {
			temp = basketList[start + idx];
			basketList[start + idx] = basketList[end - idx];
			basketList[end - idx] = temp;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine().trim());
		int basketCnt = Integer.parseInt(st.nextToken());
		int laps = Integer.parseInt(st.nextToken());

		basketInit(basketCnt);

		for (int idx = 0; idx < laps; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			reverse(start - 1, end - 1);
		}

		for (int num : basketList) {
			System.out.printf("%d ", num);
		}
	}
}
