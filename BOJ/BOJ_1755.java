package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author MuRing
 * 79를 영어로 읽되 숫자 단위로 하나씩 읽는다면 "seven nine"이 된다. 
 * 80은 마찬가지로 "eight zero"라고 읽는다. 79는 80보다 작지만, 영어로 숫자 하나씩 읽는다면 "eight zero"가 "seven nine"보다 사전순으로 먼저 온다.
 * 문제는 정수 M, N(1 ≤ M ≤ N ≤ 99)이 주어지면 M 이상 N 이하의 정수를 숫자 하나씩 읽었을 때를 기준으로 사전순으로 정렬하여 출력하는 것이다.
 * 
 * 1. 첫재 줄에 숫자의 시작과 끝 범위가 주어진다.
 * 2. 해당 범위 내의 숫자들을 정렬해서 결과를 출력한다.
 */

class Word implements Comparable<Word>{
	String str;
	int number;
	
	public Word(String str, int number) {
		this.str = str;
		this.number = number;
	}

	@Override
	public int compareTo(Word o) {
		// String만 가지고 사전 순 정렬
		
		return str.compareTo(o.str);
	}
	
	
}

public class BOJ_1755 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final String[] numString = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
	
	
	
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		PriorityQueue<Word> que = new PriorityQueue<>();
		
		// 시작과 끝 범위 저장
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 범위 내의 모든 수를 문자열과 숫자가 저장되는 Word 객체에 저장하여 우선순위 큐에 저장한다.
		for(int number = start; number <= end; number++) {
			sb.setLength(0);	// 매 숫자 문자열을 새로 받기 위해 초기화
			// 숫자가 10미만이라면 한자리기 때문에 하나만 저장되게 한다.
			if(number < 10)
				sb.append(numString[number]);
			// 두자리의 수라면 띄어씨와 다음 단어가 같이 저장되도록 처리한다.
			else 
				sb.append(numString[number / 10]).append(" ").append(numString[number % 10]);
				
			// 우선순위 큐에 해당 숫자의 문자열의 정보와 숫자 정보를 같이 저장한다.
			que.add(new Word(sb.toString(), number));
		}
		
		sb.setLength(0);	// 결과물을 출력하기 전 사용했던 sb 초기화
		
		int count = 0; 		// 한 줄에 10개씩 출력하게 하기 위한 변수
		
		while(!que.isEmpty()) {		// 큐가 남아있는 동안은 계속 반복
			Word word = que.poll();	// 큐에서 정렬된 숫자를 하나씩 빼온다.
			count++;				// 몇번째 출력인지 센다.
			
			sb.append(word.number).append(" ");	// 결과 저장
			// 만약 count가 10의 배수에 도달하면
			if(count % 10 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}	// main end
}
