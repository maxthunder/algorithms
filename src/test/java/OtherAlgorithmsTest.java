import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OtherAlgorithmsTest {

    @Test
    public void countClumpsTest() {
        assertThat(OtherAlgorithms.countClumps(new int[]{1, 1, 1, 1, 1}), is(1));
    }

    @Test
    public void seriesUpTest() {
        assertThat(OtherAlgorithms.seriesUp(1), is(new int[]{1}));
        assertThat(OtherAlgorithms.seriesUp(2), is(new int[]{1,1,2}));
        assertThat(OtherAlgorithms.seriesUp(3), is(new int[]{1,1,2,1,2,3}));
    }


}
