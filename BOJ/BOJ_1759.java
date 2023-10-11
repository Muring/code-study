package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 암호 만들기
 * 
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두개의 자음으로 되어있다.
 * 알파벳들은 오름차순 정렬이 되어있다.
 * 암호로 사용했을 법한 문자의 종류는 C가지가 있다.
 * C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하라.
 * 
 * 1. 첫째 줄에 두 정수 L, C가 주어진다. -> L은 완성된 암호의 길이, C는 주어지는 문자 개수
 * 2. 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 중복X
 * 
 * 주어지는 알파벳으로 푸는 조합 문제가 아닐까?
 * 먼저 주어지는 문자들을 정렬하고 이의 조합을 구하면 자동으로 정렬이 되어있을 것이다.
 * @author sehyeon.eom
 *
 */


public class BOJ_1759 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int codeLen;		// 완선된 암호의 길이
	static int letterCount;	// 주어지는 문자 개수
	static char letters[];	// 주어지는 문자 저장 배열
	static char codeComb[];	// 조합으로 생성된 암호문
	
	// 조합 및 기능 수행 메서드
	private static void Comb(int start, int count) {
		
		// 암호문 완성시 조건에 부합하는 경우에만 결과 저장
		if(count == codeLen) {
			int vowelCount = 0;	// 모음의 개수
			int consCount = 0;	// 자음 개수
			
			// 모음과 자음 개수 확인하면서 스트링으로 변환
			String str = "";
			for(int idx = 0; idx < codeLen; idx++) {
				if(codeComb[idx] == 'a' || codeComb[idx] == 'e' || codeComb[idx] == 'i'
						|| codeComb[idx] == 'o' || codeComb[idx] == 'u')
					vowelCount++;
				else
					consCount++;
				str += codeComb[idx];
			}
			
			// 조건에 부합하면 결과 저장
			if(vowelCount > 0 && consCount > 1)
				sb.append(str).append("\n");
			
			return;
		}
		
		// 조합 생성
		for(int idx = start; idx < letterCount; idx++) {
			codeComb[count] = letters[idx];
			Comb(idx + 1, count + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫째 줄 입력
		st = new StringTokenizer(br.readLine());
		codeLen = Integer.parseInt(st.nextToken());
		letterCount = Integer.parseInt(st.nextToken());
		
		// 주어진 입력으로 배열 생성
		letters = new char[letterCount];
		codeComb = new char[codeLen];
		
		// 둘째 줄 입력 - 문자 종류가 주어진다.
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < letterCount; idx++) {
			letters[idx] = st.nextToken().charAt(0);
		}
		
		// 주어진 문자 종류 정렬
		Arrays.sort(letters);
		
		// 암호 구하기
		Comb(0, 0);
		
		// 결과 출력
		System.out.println(sb);
	}
}
