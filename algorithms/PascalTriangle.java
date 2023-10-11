package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PascalTriangle {
	static BufferedReader br;
	static StringBuilder sb;

	static int input;

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		System.out.print("Enter the number of rows to be printed : ");
		;
		input = Integer.parseInt(br.readLine().trim());

	}

	/**
	 * line1 		    0C0
	 * line2  		 1C0   1C1
	 * line3      2C0   2C1   2C2
	 * line4   3C0   3C1   3C2   3C3
	 * 
	 * C(line, idx) = C(line, idx-1) * (line - idx + 1) / i
	 * @param line = 행
	 * @param idx = 몇번째 숫자인지 
	 */
	private static void pascal() {
		for (int line = 1; line <= input; line++) {
			for (int idx = 0; idx <= input - line; idx++) {
				// 좌 공백
				sb.append(" ");
			}

			int C = 1;
			for (int idx = 1; idx <= line; idx++) {
				// 삼각형의 첫 값은 항상 1이다.
				sb.append(C).append(" ");
				C = C * (line - idx) / idx;
			}
			sb.append("\n");
		}

	}

	private static void print() {
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		init();

		pascal();

		print();
	}
}