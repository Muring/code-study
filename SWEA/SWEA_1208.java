package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Flatten - 평탄화 
 * 쌓여 있는 상자들을 평탄화 하는 작업 
 * 1. 가장 높은 곳에 있는 상자를 가장 낮은 곳에 내린다. 
 * 2. 주어진 덤프 횟수만큼 평탄화 반복 
 * 3. 이후 최고점과 최저점의 높이 차이 출력
 * 
 * 가로는 항상 100 상자의 높이는 항상 1이상 100이하 덤프 횟수는 1이상 1000이하 
 * 주어진 덤프 횟수 이내에 평탄화가 완료되면 높이 차 반환 -> 0 or 1
 */

public class SWEA_1208 {
	static BufferedReader bf; 	// 입력
	static int dumpCount;		// 덤프 횟수
	static final int col = 100;	// 가로 길이
	static ArrayList<Integer> box = new ArrayList<>(col); // 각 가로열에 들어갈 박스 갯수가 들어갈 배열
	static String[] temp = new String[col]; // int형으로 변환하기 전 임시 저장 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 선언만 되어있던 입출력 객체 선언
		bf = new BufferedReader(new InputStreamReader(System.in));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			box.clear();	// 박스 리스트 초기화
			dumpCount = Integer.parseInt(bf.readLine().trim());	// 최대 텀프 횟수 입력
			
			temp = bf.readLine().trim().split(" ");		// 임시 배열에 각 열의 박스 갯수 저장
			for(int idx = 0; idx < col; idx++)
				box.add(Integer.parseInt(temp[idx]));	// 박스 갯수 int형으로 저장
				
			Collections.sort(box);	// 박스 높이 순 정렬
			
			// 최소 박스와 최대 박스 한차례 교환 후 재정렬 반복
			// 이렇게 하면 맨 앞과 맨 뒤의 박스만 교환하면 됨
			for(int dumpIdx = 0; dumpIdx < dumpCount; dumpIdx++) {
				box.set(0, box.get(0) + 1);
				box.set(99, box.get(99) - 1);
				Collections.sort(box);	// 박스 높이 순 정렬
			}
			// 결과 출력
			System.out.printf("#%d %d\n", test_case, box.get(99) - box.get(0));
		}
	}
}
