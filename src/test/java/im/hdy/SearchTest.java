package im.hdy;

import im.hdy.utils.SearchUtils;
import org.junit.Test;

/**
 * Created by hdy on 09/02/2018.
 */
public class SearchTest {

    @Test
    public void test() {
        String search = SearchUtils.search(0, "鬼", 2, 10);
        System.out.println(search);
    }

}
