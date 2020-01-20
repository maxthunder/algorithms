import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGen {

    public static List<Integer> numberGen(long seed, int runSize) {
        List<Integer> list;
        Random rand = new Random(seed);
        return IntStream.range(0, runSize).mapToObj(i -> Math.abs(rand.nextInt() % 100)).collect(Collectors.toList());
    }
}
