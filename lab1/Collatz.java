/** Class that prints the Collatz sequence starting from a given number.
 *  @author Hyperbolic Triangle
 */
public class Collatz {

    /** Takes int `n` and returns next number in Collatz Sequence */
    public static int nextNumber(int n) {
        if (n  == 1) {
            return 1;
        } else if (n%2 == 0) {
            return n / 2;
        } else {
            return 3 * n + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        while (n != 1) {
            System.out.print(n + " ");
            n = nextNumber(n);
        }
        System.out.print(n);
    }
}

