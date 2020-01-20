import java.util.Collections;
import java.util.List;

public class AlgorithmTesting {

    protected final static long seed = System.nanoTime();
    protected final static int runSize = 40_000;
    protected static List<Integer> unsortedList;
    protected static List<Integer> expected;

    public AlgorithmTesting() {
        setup();
    }

    public static void reset() {
        setup();
    }

    private static void setup() {
        unsortedList = Collections.unmodifiableList(RandomGen.numberGen(seed, runSize));
        expected = RandomGen.numberGen(seed, runSize);
        Collections.sort(expected);
    }
}
