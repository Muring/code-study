package algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SieveOfErastosthenes {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int input;
	static boolean[] prime;
	
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		System.out.println("You can know prime numbers smaller than or equal to your input number");
		System.out.print("Enter the number : ");;
		input = Integer.parseInt(br.readLine().trim());

	}
	
	/**
	 * 2부터 숫자를 확인한다.
	 * 해당 숫자의 boolean 값이 true이면 소수라는 의미
	 * 이후 해당 숫자의 배수는 모두 false로 바꾼다.
	 * 이후 위의 과정을 반복
	 * 
	 * primeIdx * primeIdx를 하는 이유는 그 이내의 숫자는 소수이거나
	 * 이미 앞의 숫자의 배수로 정리가 되어있을 것이기 때문에 확실히 걸러지지 않았을 
	 * 해당 숫자의 제곱수 이후부터 계산하는 것이다.
	 */
	private static void sieve() {		
		// 받은 입력값으로 배열 생성
		prime = new boolean[input + 1];
		for(int idx = 0; idx <= input; idx++) {
			prime[idx] = true;
		}
		
		for(int primeIdx = 2; primeIdx * primeIdx <= input; primeIdx++) {
			// prime[primeIdx]가 true이면 이는 소수라는 의미이다.
			if(prime[primeIdx] == true) {
				// 소수를 찾았으면 해당 소수의 배수를 모두 false처리한다.
				for(int idx = primeIdx * primeIdx; idx <= input; idx += primeIdx) {
					prime[idx] = false;
				}
			}
		}
	}
	
	private static void print() {
		for(int idx = 2, count = 0; idx <= input; idx++) {
			if(prime[idx] == true) {			
				if(count % 10 == 0)
					sb.append("\n");
				sb.append(idx).append(" ");
				count++;
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		sieve();
		
		print();
	}
}
