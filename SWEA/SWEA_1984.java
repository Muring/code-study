package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 중간 평균 값 구하기
 * 
 * 10개의 수를 입력 받아, 최대 수와 최소 수를 제외한 나머지의 평균값을 출력하는 프로그램을 작성하라.
 * (소수점 첫째 자리에서 반올림한 정수를 출력한다.)
3      
3 17 1 39 8 41 2 32 99 2 
22 8 5 123 7 2 63 7 3 46 
6 63 2 3 58 76 21 33 8 1   

 * @author sehyeon.eom
 *
 */

public class SWEA_1984 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int[] numArr;	// 전체 숫자 저장 배열
	static final int TOTAL_NUMBER = 10;	// 전체 입력받는 숫자 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {

			numArr = new int[TOTAL_NUMBER];
			
			// 배열에 숫자 입력 및 저장
			st = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < TOTAL_NUMBER; idx++) {
				numArr[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 배열 오름차순 정렬
			Arrays.sort(numArr);
			
			// 이제 맨 앞과 맨 뒤가 각각 최소 최대이기 때문에 이를 배고 계산한다.
			float sum = 0;
			for(int idx = 1; idx < TOTAL_NUMBER - 1; idx++)
				sum += numArr[idx];
			
			sum /= TOTAL_NUMBER - 2;
			
			// 반올림
			System.out.printf("#%d %.0f\n", tc, sum);
		}	// tc end
	}	// main end
}	
