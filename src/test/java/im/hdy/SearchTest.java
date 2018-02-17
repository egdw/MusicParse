package im.hdy;

import com.sun.jdi.PathSearchingVirtualMachine;
import im.hdy.model.Music;
import im.hdy.utils.MusicUtils;
import im.hdy.utils.SearchUtils;
import org.junit.Test;
import sun.applet.Main;

import javax.sound.midi.Soundbank;

/**
 * Created by hdy on 09/02/2018.
 */
public class SearchTest {

    @Test
    public void test() {
        String search = SearchUtils.search(10, "邓紫棋", 1, 10);
        System.out.println(search);
    }

    @Test
    public void test2() {
        Music music = MusicUtils.get(10, "41c2e4ab5660eae04021c5893e055f50");
        System.out.println(music);
    }

//    public static Object[] o = new Object[5];
//
//    public static void main(String[] args) {
//        o[0] = 1;
//        System.out.println(o);
//    }
}
