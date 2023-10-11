package BOJ;
/**
 * 
 * @author sehyeon.eom
 * 
 * 신기한 소수
 * 
 * 어떤 자리수로 봐도 소수인 소수를 전부 출력하라!
 * 
 * 1. 몇자리 수 인지 입력
 * 2. 해당 자리 수의 신기한 소수 모두 출력
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int digit;	// 몇 자리 수 인지
	
	// 특별한 소수를 찾자
	// num이 소수인지 판별이 되면 재귀로 들어감
	// num 뒤에 새로운 숫자를 붙여서 그 숫자가 소수인지 판별함
	// 이거를 N자리 수 까지 판별함
	// 나오는 모든 N자리 수 결과를 저장 후 출력
	private static void findSpecialPrime(int num, int count) {	// 몇 자리 수, 현재 몇번째 자리인지
		// 주어진 자리 수에 도달하면 종료
		if(count == digit)
			sb.append(num).append("\n");
		
		// 숫자기 때문에 1부터 9까지 돈다.
		for(int idx = 1; idx < 10; idx++) {
			int temp = 10 * num + idx;				// 주어진 숫자에 10을 곱해 자리수를 옮기고 idx를 더해 숫자를 만든다.
			if(count < digit && isPrime(temp))		// 만들어진 숫자가 소수인지 확인하고 맞으면 다음 자리 수로 넘어간다.
				findSpecialPrime(temp, count + 1);
		}
	}
	
	// 소수인지 확인
	private static boolean isPrime(int num) {
		if(num < 2) return false;				// 1은 소수 아니니까 예외
		for(int i = 2; i * i <= num; i++) {		// 제곱수로 나눠 소수인지 아닌지 확인
			if(num % i == 0)
				return false;
		}
		return true;							// 모든 조건을 통과하면 소수
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 몇 자리 수 인지 입력
		digit = Integer.parseInt(br.readLine().trim());
		
		findSpecialPrime(0, 0);
	
		System.out.println(sb);
	}
}
