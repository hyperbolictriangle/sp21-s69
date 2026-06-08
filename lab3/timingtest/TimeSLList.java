package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> N = new AList<>();
        AList<Integer> opCounts = new AList<>();
        AList<Double> times = new AList<>();
        SLList<Integer> lst = new SLList<>();
        int mul = 1;
        int M = 10000; // number of times getLast is called.

        for (int i = 1; i <= 128000; i++) {
            lst.addLast(i);
            if (i == mul * 1000) {
                // now start timer and perform M getLast operations.
                Stopwatch time = new Stopwatch();
                for (int j = 1; j <= M; j++) {
                    lst.getLast();
                }
                N.addLast(i);
                opCounts.addLast(M);
                times.addLast(time.elapsedTime());
                mul <<= 1;
            }
        }
        printTimingTable(N, times, opCounts);
    }

}
