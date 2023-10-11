package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1로 만들기
 * 
 * 	정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지이다.
 * 	- X가 3으로 나누어 떨어지면, 3으로 나눈다.
 *  - X가 2로 나누어 떨어지면, 2로 나눈다.
 *  - 1을 뺀다.
 *  정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 
 *  연산을 사용하는 횟수의 최솟값을 출력하시오.
 *  
 *  [입력]
 *  첫째 줄에 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N이 주어진다.
 *  
 *  [출력]
 *  첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *  
 *  1. 정수가 주어진다.
 *  2. 계산 시작
 * 		- 3으로 나누기
 * 		- 2로 나누기
 * 		- 1을 빼고 전에 저장된 배열 값  +1로 즉시 저장한다.
 * @author sehyeon.eom
 *
 */

public class BOJ_1463 {
	static BufferedReader br;
	
	static Integer[] dpArr;
	
/*
	private static int recur(int input, int count) {
		if(input < 2)
			return count;
		
		return Math.min(recur(input / 2, count + 1 + (input % 2)), recur(input / 3, count + 1 + (input % 3)));
	}
*/
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 정수가 주어진다.
		int inputNum = Integer.parseInt(br.readLine());
		// 계산 시작
		// 정수니까 +1 처리
		dpArr = new Integer[inputNum + 1];
		dpArr[0] = dpArr[1] = 0;
		
		for(int idx = 2; idx <= inputNum; idx++) {
			// 우선 전의 값의 +1을 하고
			dpArr[idx] = dpArr[idx - 1] + 1;
			
			// 해당 수의 나누는 값과 미리 계산해 놓은 값을 비교하여 더 작은 값을 저장한다.
			if(idx % 3 == 0) {
				dpArr[idx] = (dpArr[idx] > dpArr[idx / 3] + 1) ? dpArr[idx / 3] + 1 : dpArr[idx];
			}
			if(idx % 2 == 0) {
				dpArr[idx] = (dpArr[idx] > dpArr[idx / 2] + 1) ? dpArr[idx / 2] + 1 : dpArr[idx];
			}

		}
		System.out.println(dpArr[inputNum]);
//		System.out.println(recur(inputNum, 0));
	}	// main end
}
