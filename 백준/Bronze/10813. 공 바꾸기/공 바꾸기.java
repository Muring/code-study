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

		// 바구니 수와 공을 바꿀 횟수 입력
		st = new StringTokenizer(br.readLine());
		int total = Integer.parseInt(st.nextToken());
		int lap = Integer.parseInt(st.nextToken());

		// 바구니에 번호 저장
		int[] basketList = new int[total];
		for (int idx = 0; idx < total; idx++)
			basketList[idx] = idx + 1;

		// lap 돌
		for (int idx = 0; idx < lap; idx++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;

			int temp = basketList[start];
			basketList[start] = basketList[end];
			basketList[end] = temp;
		}
		for (int num : basketList)
			System.out.print(num + " ");
	}
}