package tests.math.gcd;

import java.math.BigInteger;

import static math.number_theory.gcd.GreaterCommonDivisor.gcd;

public class GreaterCommonDivisorTest {
    private static final int TESTS = 100000;
    public static final int MIN = 0;
    public static final int MAX = 100000;

    public static void main(String[] args) {
        for(int i = 0; i < TESTS; i++) {
            int a = (int) ((((MAX - MIN) + 1) * Math.random()) + MIN);
            int b = (int) ((((MAX - MIN) + 1) * Math.random()) + MIN);
            int[] result = testCase(a, b);
            checkResult(a, b, result);
        }
    }

    private static void checkResult(int a, int b, int[] result) {
        if(result[0] != result[1]) {
            System.out.println(
                    "Mismatch for input (" + a + ", " + b +
                            "): expected " + result[0] + " ,got " + result[1]
            );
        }
    }

    private static int[] testCase(int a, int b) {
        BigInteger bigIntegerA = BigInteger.valueOf(a);
        BigInteger bigIntegerB = BigInteger.valueOf(b);
        int referenceGcd = bigIntegerA.gcd(bigIntegerB).intValue();
        int gcd = gcd(a, b);
        return new int[] { referenceGcd, gcd };
    }
}
