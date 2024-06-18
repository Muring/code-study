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
		sb = new StringBuilder();

		// 입력 받을 정수의 개수 입력
		st = new StringTokenizer(br.readLine().trim());
		int totalNum = Integer.parseInt(st.nextToken());

		// 정수 입력
		int[] numList = new int[totalNum];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < totalNum; idx++) {
			numList[idx] = Integer.parseInt(st.nextToken());
		}

		// 찾을 수 입력
		st = new StringTokenizer(br.readLine().trim());
		int searchNum = Integer.parseInt(st.nextToken());

		int answer = 0;
		for (int idx = 0; idx < totalNum; idx++) {
			if (numList[idx] == searchNum)
				answer++;
		}

		System.out.println(answer);
	}
}