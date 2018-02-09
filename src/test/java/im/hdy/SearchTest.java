package im.hdy;

import org.junit.Test;

/**
 * Created by hdy on 09/02/2018.
 */
public class SearchTest {

    @Test
    public void test() {
        String search = SearchUtils.search(7, "鬼", 1, 10);
        System.out.println(search);
    }

}
