import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static BigInteger findPrimeWithBigInteger(long num) {
        BigInteger bigNumber = new BigInteger(String.valueOf(num));
        if (bigNumber.isProbablePrime(10)) return bigNumber;
        else return bigNumber.nextProbablePrime();
    }

    private static long findPrimeWithSqrt(long num) {
        while (true) {
            long count = 0;
            for (long idx = 2; idx <= Math.sqrt(num); idx++) {
                if (num % idx == 0) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                if (num == 0 || num == 1) {
                    num = 2;
                }
                break;
            }
            num++;
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            long num = Long.parseLong(br.readLine());
//            sb.append(findPrimeWithBigInteger(num)).append("\n");
            sb.append(findPrimeWithSqrt(num)).append("\n");
        }

        System.out.println(sb);
    }
}