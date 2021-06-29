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
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int[] Narray = {1000, 2000, 4000, 8000, 16000, 32000, 64000};
        int ops = 10000;
        for (int i = 0; i < Narray.length; i++) {
            SLList<Integer> testList = new SLList<>();
            for (int j = 0; j < Narray[i]; j++) {
                testList.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int k = 0; k < ops; k++) {
                testList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            Ns.addLast(Narray[i]);
            opCounts.addLast(ops);
        }
        printTimingTable(Ns,times, opCounts);
    }

}
