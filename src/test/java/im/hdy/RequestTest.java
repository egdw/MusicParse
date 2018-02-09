package im.hdy;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by hdy on 09/02/2018.
 */
public class RequestTest {
    @Test
    public void test(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("kw","123");
        map.put("core","all");
        map.put("page","1");
        map.put("rows","10");
        map.put("is_paid","false");
        String s = RequestUtils.get("http://search.ximalaya.com/front/v1", map);
        System.out.println(s);
    }

}
