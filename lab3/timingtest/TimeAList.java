package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(timingtest.AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> N = new AList<>();
        timingtest.AList<Double> time = new AList<>();
        int[] Narray = {1000, 2000, 4000, 8000, 16000, 32000, 64000};
        for (int i = 0; i < Narray.length; i++) {
            AList<Integer> testList = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < Narray[i]; j++) {
                testList.addLast(j);
            }
            double timeInSeconds = sw.elapsedTime();
            time.addLast(timeInSeconds);
            N.addLast(Narray[i]);
        }
        printTimingTable(N,time, N);
    }
}
