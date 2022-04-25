import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Proj6Test {
    @Test
    public void doubleIncrementerTest1() throws Exception {
        int a = 1;
        int b = 10;
        int c = 100;

        System.out.println(a+b+c);
        a += b += c;
        assertThat(a, is(111));
        assertThat(b, is(110));
        assertThat(c, is(100));
    }

    @Test
    public void doubleIncrementerTest2() throws Exception {
        int a = 0;
        int b = 0;
        int c = 100;

        System.out.println(a+b+c);
        a += b += c;
        assertThat(a, is(100));
        assertThat(b, is(100));
        assertThat(c, is(100));
    }

}