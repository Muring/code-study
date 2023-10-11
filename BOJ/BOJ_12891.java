package BOJ;

import java.util.ArrayList;

/**
 * 
 * @author sehyeon.eom
 * 
 * DNA password
 * 
 * All DNA letters are made with A C G T
 * To use subString as a password, it has to contain required numbers of letter above
 * 
 * 1. input DNA String length and String length
 * 2. input the DNA String
 * 3. input minimum counts that subString has to contain in A C G T
 * 4. print how many kinds of password you can make
 *
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12891 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int DNA_LEN;				// DNA length
	static int PASSWORD_LEN;		// Password length
	static int[] minRequired;		// minimum requirements
	static final int reqCount = 4;	// how many requirement types?
	static ArrayList<Character> DNA;// full DNA
	static int answer = 0;			// answer
	static int[] count;				// Counting each elements A C G T
	
	private static void confirm(int start, int end, int[] count) {
		// Count how many conditions it has passed
		// If the flag reaches 4 it means it passed all the conditions so add 1 to answer
		int flag = 0; 	
		for(int idx = 0; idx < reqCount; idx++) {
			// If it fits the condition
			if(count[idx] >= minRequired[idx]) {
				flag++;
			}
		}
		
		// When it passed all the conditions
		if(flag == 4) answer++;
		
		// After adding answer, shift value of the password element count to right
		// Subtract the very first count;
		if(DNA.get(start) == 'A') count[0] -= 1;
		else if(DNA.get(start) == 'C') count[1] -= 1;
		else if(DNA.get(start) == 'G') count[2] -= 1;
		else if(DNA.get(start) == 'T') count[3] -= 1;
		

		if(end == DNA_LEN) return;
		if(DNA.get(end) == 'A') count[0] += 1;
		else if(DNA.get(end) == 'C') count[1] += 1;
		else if(DNA.get(end) == 'G') count[2] += 1;
		else if(DNA.get(end) == 'T') count[3] += 1;
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// input DNA length and string length
		st = new StringTokenizer(br.readLine());
		DNA_LEN = Integer.parseInt(st.nextToken());
		PASSWORD_LEN = Integer.parseInt(st.nextToken());
		
		// input DNA to ArrayList
		// ArrayList will be C C T G G A T T G
		DNA = new ArrayList<>(DNA_LEN);
		char[] temp = br.readLine().toCharArray();
		for(int idx = 0; idx < DNA_LEN; idx++)
			DNA.add(idx, temp[idx]);
		
		// And now you input the minimum requirements
		minRequired = new int[reqCount];
		st = new StringTokenizer(br.readLine());
		for(int idx = 0; idx < reqCount; idx++)
			minRequired[idx] = Integer.parseInt(st.nextToken());
		
		// All the inputs are done
		// Now it's time to make the result
		
		count = new int[reqCount];	
		// Initial count how many A C G T are in the password
		for(int idx = 0; idx < PASSWORD_LEN; idx++) {
			if(DNA.get(idx) == 'A') count[0] += 1;
			else if(DNA.get(idx) == 'C') count[1] += 1;
			else if(DNA.get(idx) == 'G') count[2] += 1;
			else if(DNA.get(idx) == 'T') count[3] += 1;
		}
		
		// Now add and delete one by one by add and remove
		for(int idx = 0; idx <= DNA_LEN - PASSWORD_LEN; idx++)
			confirm(idx, idx + PASSWORD_LEN, count);
			
		// Save the result
		sb.append(answer);
		// Print result	
		System.out.println(sb);
	}
}
