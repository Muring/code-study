package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458 {
	// 기본 입력 출력을 위한 객체 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static long supervisorCount;		// 전체 감독관 수
		
	public static void main(String[] args) throws IOException {
		// 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 시험장 수 입력
		int siteCount = Integer.parseInt(br.readLine().trim());
		
		// 각 시험장에 존재하는 학생 수 입력
		int[] site = new int[siteCount];	// 각 사이트에는 학생 수가 저장되어 있다.
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < siteCount; idx++)
			site[idx] = Integer.parseInt(st.nextToken());
		
		// 감독관이 커버할 수 있는 학생 수 입력
		int[] ableCount = new int[2];
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < ableCount.length; idx++)
			ableCount[idx] = Integer.parseInt(st.nextToken());
		
		// 필요한 감독관 수 구하기
		supervisorCount = 0;	// 기본 초기화
		for(int roomIdx = 0; roomIdx < siteCount; roomIdx++) {
			if(site[roomIdx] <= ableCount[0]) {		// 그 방의 인원수가 총감독관이 전부 감독할 수 있는 숫자라면
				supervisorCount++;
				continue;
			}
			else if(site[roomIdx] > ableCount[0]) {	// 그 방의 인원수가 총감독관이 전부 감독할 수 없는 숫자라면
				supervisorCount++;
				int temp = site[roomIdx] - ableCount[0];// temp변수에 총감독관이 감독하는 인원수를 뺀 나머지를 저장
				if(temp % ableCount[1] == 0)	// 남은 방의 인원이 정확히 나누어 떨어진다면
					supervisorCount += temp / ableCount[1];
				else									// 나누어 떨어지지 않는다면
					supervisorCount += temp / ableCount[1] + 1;
			}
		}
		
		sb.append(supervisorCount);
		System.out.println(sb);
	}
}
