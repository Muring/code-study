package BOJ;

import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author SeHyeon.Eom
 * 
 * 스위치 켜고 끄기
 * 
 * 순서대로 받은 수와 성별에 따라 스위치의 상태를 바꾼다.
 * 
 * 남학생은 스위치 번호가 자기가 받은 수의 배수이면 스위치 상태를 바꾼다.
 * 
 * 여학생은 자기가 바든 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서
 * 그 구간에 속한 모든 스위치의 상태를 바꾼다. 
 * -> 받은 번호를 중심으로 좌우 확인하면서 좌우가 짝이 맞으면 안맞는 스위치가 나올 때까지 계속 바꿈
 * 
 * 1. 스위치 개수 입력 -> 100 이하의 양의 정수
 * 2. 스위치 상태 입력 -> 켜져있으면 1, 꺼져있으면 0, 사이에 빈칸 하나씩
 * 3. 학생 수 입력 -> 100 이하의 양의 정수
 * 4. 학생 수 만큼 학생의 성별 및 학생이 받은 수 입력 -> 남학생 1, 여학생 2, 학생이 받은 수는 스위치 이하의 양의 정수 개수
 * 5. 스위치는 한줄에 20개까지 출력된다 -> 21번쨰 스위치는 두번째 줄에 출력
 * 
 */
public class BOJ_1244 {
	static int switchCount;				// 스위치 갯수
	static int[] switches;				// 스위치 배열
	static int studentCount;			// 학생 수
	
	// 성별 별 스위치 조작 메서드
	public static void interact(int sex, int location) {
		// 남성
		if(sex == 1) {			
			// 배수에 해당하는 스위치 전부 조작
			for(int idx = 0; idx < switchCount; idx++){
				if((idx + 1) % location == 0)
					switchInteract(idx);
			}
		}
		// 여성
		else if(sex == 2) {
			switchInteract(location - 1);											// 먼저 해당 스위치를 조작
			for(int idx = 1; idx < switchCount / 2; idx++){							// 좌우로 확인하기 때문에 전체 스위치 개수의 반만큼
				if(location - 1 + idx >= switchCount || location - 1 - idx < 0)		// 좌우의 범위가 스위치의 범위를 벗어나면 종료
					break;
				if(switches[location - 1 - idx] == switches[location - 1 + idx]){	// 좌우가 같다면
					switchInteract((location - 1) - idx);							// 좌 스위치 조작
					switchInteract((location - 1) + idx);							// 우 스위치 조작
				}
				else
					break;
			}
		}
	}
	
	// 직접적인 스위치 조작
	public static void switchInteract(int location) {
		switches[location] = 1 - switches[location];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);						// 스캐너 객체 생성

		switchCount = sc.nextInt();									// 스위치 개수 입력
		switches = new int[switchCount];							// 스위치 배열 객체 생성
		for(int idx = 0; idx < switchCount; idx++)
			switches[idx] = sc.nextInt();							// switches 리스트에 스위치 정보 저장
		
		int sex, location;
		studentCount = sc.nextInt();								// 학생 수 입력
		for(int idx = 0; idx < studentCount; idx++) {				// 학생 수 만큼 조작 시작
			sex =  sc.nextInt();									// 학생 성별 정보 저장
			location =  sc.nextInt();								// 학생 스위치 조작 시작 위치 저장
			 
			interact(sex, location);								// 스위치 조작
		}

		for(int idx = 0; idx < switchCount; idx++){					// 결과 출력
			System.out.printf("%d\n", switches[idx]);
		}

		sc.close();													// 스캐너 닫기
	}
}
