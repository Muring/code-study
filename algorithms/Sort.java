package algorithms;

import java.util.Arrays;

/**
 * 
 * @author sehyeon.eom
 * 
 * 삽입, 카운팅, 퀵 정렬을 구현한다.
 * 원본 배열이 손상되지 않도록 복사하여 사용한다.
 *
 */
public class Sort {
	static int[] originArr;
	static final int SIZE = 1000;	// 배열의 크기
	
	private static void init() {
		originArr = new int[SIZE];
		for(int idx = 0; idx < originArr.length; idx++) {
			originArr[idx] = (int)(Math.random()*31);	// 0 ~ 30
		}
		
		System.out.println("============ Before Sort ============");
		System.out.println(Arrays.toString(originArr));
		System.out.println();
	}
	
	// 삽입 정렬
	private static void insertSort(int[] originArr) {		
		int[] arr = originArr.clone();
		long beforeTime = System.nanoTime(); //코드 실행 전에 시간 받아오기
		
		int insertIdx = 0;
		// 정렬 인덱스는 시작점부터 끝점까지 진행한다.
		for(int idx = 1; idx < arr.length; idx++) {
			// 정렬 인덱스에 해당하는 배열 값을 따로 빼내어 저장한다.
			int num = arr[idx];
			// 빼낸 인덱스의 앞의 배열 값과 모두 비교한다.
			// 비교한 값보다 빼낸 값이 더 작다면 비교한 값을 한칸 뒤로 옮긴다.
			for(insertIdx = idx - 1; insertIdx >= 0 && arr[insertIdx] > num; insertIdx--) {
				arr[insertIdx + 1] = arr[insertIdx]; 
			}
			// 마지막 빈자리에 저장한 값 저장
			arr[insertIdx + 1] = num;
		}
		
		long afterTime = System.nanoTime(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
		printSortedArr(arr, secDiffTime);
	}
	
	private static void countingSort(int[] originArr) {
		int[] arr = originArr.clone();
		long beforeTime = System.nanoTime(); //코드 실행 전에 시간 받아오기
		
		int[] count = new int[31];	// 수의 범위 : 0 ~ 30
		int[] result = new int[SIZE];	// 정렬 될 배열 
		
		// count 배열에 각 숫자 개수 세기
		for(int idx = 0; idx < arr.length; idx++) {
			count[arr[idx]]++;
		}
		
		// 이후 각 항목의 앞에 위치할 항목의 개수를 반영하기 위해 count 배열 조정
		for(int idx = 1; idx < count.length; idx++) {
			count[idx] += count[idx - 1];
		}
		
		// 수열의 뒤에서부터 확인하며 수열의 값을 인덱스로하는 count 값을 감소시키고 해당 감소된 값을 인덱스로하는 temp배열에 수열의 값을 넣는다.
		for(int idx = arr.length - 1; idx >= 0; idx--) {
			count[arr[idx]]--;
			result[count[arr[idx]]] = arr[idx];
		}
		
		long afterTime = System.nanoTime(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
		printSortedArr(result, secDiffTime);
	}
	
	/**
	 * 왼쪽 피벗 선택 방식
	 * 왼쪽 피벗을 기준으로 오른쪽의 모든 값들을 약식 정렬한다.
	 * 약식 정렬 이후 left와 right가 겹쳐진 부분과 pivot의 위치를 바꾸고,
	 * 바뀐 pivot을 기준으로 좌우 정렬을 진행한다.
	 * 
	 * @param arr	정렬할 배열
	 * @param left	현재 부분배열의 왼쪽
	 * @param right 현재 부분배열의 오른쪽
	 */
	private static void quickSort(int[] arr, int left, int right) {
		// 왼쪽 인덱스와 오른쪽 인덱스가 겹쳐지면 종료
		if(left >= right) {
			return;
		}
		
		// 퀵 정렬을 진행하기 전 기준인 pivot을 구한다.
		int pivot = partition(arr, left, right);
		
		// 구한 pivot을 기준으로 퀵 정렬을 진행한다.
		quickSort(arr, left, pivot - 1);
		quickSort(arr, pivot + 1, right);
	}
	
	// 피벗을 기준으로 부분 정렬 메소드
	private static int partition(int[]arr, int left, int right) {
		int partLeft = left;
		int partRight = right;
		int pivot = arr[left];	// 부분 배열의 왼쪽 요소를 pivot으로 설정
		
		// partLeft가 partRight보다 작을때 반복하면 된다.
		while(partLeft < partRight) {
			// partRight가 partLeft보다 크면서 partRight의 요소가 pivot보다 작은 값을 찾는다.
			while(partLeft < partRight && arr[partRight] > pivot) {
				partRight--;
			}
			
			// partLeft가 partRight보다 작으면서 partLeft의 요소가 pivot보다 큰 값을 찾는다.
			while(partLeft < partRight && arr[partLeft] <= pivot) {
				partLeft++;
			}
			
			// 값을 다 찾았으면 해당 값을 교환한다.
			swap(arr, partLeft, partRight);
		}
		
		// 마지막으로 맨 처음 pivot으로 설정했던 위치의 원소와 partLeft가 가리키는 원소를 바꾼다.
		swap(arr, left, partLeft);
		
		return partLeft;
	}
	
	// 숫자 두 개의 위치를 바꾸는 메소드
	private static void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
	
	// 정렬된 배열을 출력하는 출력 메소드
	private static void printSortedArr(int[] arr, long secDiffTime) {
		System.out.println("============ After Sort ============");
		System.out.println(Arrays.toString(arr));
		System.out.println("걸린 시간 : " + secDiffTime +"(n/s)");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		// 시작 설정
		init();
		
		// 삽입
		System.out.println("INSERT SORT");
		insertSort(originArr);
		
		// 카운팅
		System.out.println("COUNTING SORT");
		countingSort(originArr);
		
		// 퀵
		System.out.println("QUICK SORT");
		int[] arr = originArr.clone();
		long beforeTime = System.nanoTime(); //코드 실행 전에 시간 받아오기
		
		quickSort(arr, 0, arr.length - 1);
		
		long afterTime = System.nanoTime(); // 코드 실행 후에 시간 받아오기
		long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
		printSortedArr(arr, secDiffTime);
	}
}
