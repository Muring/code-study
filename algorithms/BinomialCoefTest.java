package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author sehyeon.eom
 *
 */

public class BinomialCoefTest {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] binomial = new int[N + 1][K + 1];

		for (int idx = 0; idx <= N; idx++) {
			for (int j = 0, end = Math.min(idx, K); j <= end; j++) {
				if (j == 0 || idx == j)
             					binomial[idx][j] = 1;
				else
					binomial[idx][j] = binomial[idx - 1][j - 1] + binomial[idx - 1][j];
			}
		}
		System.out.println(binomial[N][K]);
	}
}
