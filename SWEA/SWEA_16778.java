package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * SWEA_17220
 * 
 * N명을 대상으로 반배치고사 실시
 * 반 배치고사 점수를 기반으로 3개의 반으로 나누어 수업 진행
 * 분반을 나누는 기준은 점수 S1, S2로 임의로 선정하여 분반한다.
 * S2 이상 = A반
 * S1 이상 S2 미만 = B반
 * S2 미만 = C반
 * 
 * 각 분반별로 주어진 최소 인원과 최대 인원을 만족해야한다.
 * 학생 수가 가장 많은 반과 가장 적은 반의 차이의 최솟 값을 구하여라.
 * 만약 각 반의 최소 인원, 최대 인원을 만족하는 점수 S1, S2가 없다면 -1을 출력
 * 
 * 1. 테스트 케이스 개수가 주어진다.
 * 2. 학생의 수, 반의 최소 인원 수, 최대 인원수가 주어진다.
 * 3. 세번째 줄에 각 학생의 점수가 주어진다.
 * 
 * @author sehyeon.eom
 *
 */

public class SWEA_16778 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int studentCount;
	static int minClassStudentNum;
	static int maxClassStudentNum;
	static int[] scoreList;	// 점수 목록
	static int[] scoreComb;
	static int flag, answer;
	
	static int[] count = new int[3];
	
	// 기준 점수의 조합을 구해 해당 조합으로 분반을 실행한다.
	private static void setScores(int s1, int s2, int count) {
		if(count == 2) {
			countClass();
			return;
		}
		
		for(int idx = s1; idx <= s2; idx++) {
			scoreComb[count] = idx;
			setScores(idx + 1, s2, count + 1);
			
		}
	}
	
	// 분반을 실행하고 해당 반들의 최소 인원수와 최대 인원 수를 구한다.
	private static void countClass() {
		Arrays.fill(count, 0);
		// scoreComb에 S1과 S2가 들어가있다.
		for(int idx = 0; idx < studentCount; idx++) {
			if(scoreList[idx] >= scoreComb[1])
				count[0]++;
			else if(scoreList[idx] >= scoreComb[0])
				count[1]++;
			else
				count[2]++;
		}
		
		int minNum = Integer.MAX_VALUE;
		int maxNum = 0;
		
		for(int idx = 0; idx < 3; idx++) {
			if(count[idx] > maxNum)
				maxNum = count[idx];
			if(count[idx] < minNum)
				minNum = count[idx];
		}
		
		// 최대 최소 조건 확인
		if(minNum >= minClassStudentNum && maxNum <= maxClassStudentNum) {
			flag = 1;
			answer = Math.min(answer, maxNum - minNum);
		}
		

	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= totalCase; tc++) {
			sb.append("#").append(tc).append(" ");
			answer = Integer.MAX_VALUE;
			
			// 학생 수, 반의 최소 인원 수, 최대 인원 수
			st = new StringTokenizer(br.readLine());
			studentCount = Integer.parseInt(st.nextToken());
			minClassStudentNum = Integer.parseInt(st.nextToken());
			maxClassStudentNum = Integer.parseInt(st.nextToken());
			
			// 학생들의 점수 입력
			scoreList = new int[studentCount];
			st = new StringTokenizer(br.readLine());
			for(int idx = 0; idx < studentCount; idx++) {
				scoreList[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 학생들의 점수 정렬
			Arrays.sort(scoreList);
			
			// S1과 S2는 내가 임의로 정해야한다.
			int minScore = scoreList[0];
			int maxScore = scoreList[scoreList.length - 1];
			scoreComb = new int[2];
			// 기준 점수 조합 구하기
			setScores(minScore, maxScore, 0);
			
			if(flag == 0)
				sb.append(-1);
			else
				sb.append(answer);
			
			sb.append("\n");
			flag = 0;
		}	// tc end
		System.out.println(sb);
	}	// main end
}
