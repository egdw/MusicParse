package im.hdy.utils;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * Created by hdy on 09/02/2018.
 * 网路请求工具
 */
public class RequestUtils {

    public static String get(String url, Map<String, String> params) {
        try {
            String back = Jsoup.connect(url).data(params).ignoreContentType(true).get().text();
            return back;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            String back = Jsoup.connect(url).data(params).headers(headers).ignoreContentType(true).get().text();
            return back;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, String> params) {
        try {
            String back = Jsoup.connect(url).data(params).ignoreContentType(true).post().text();
            return back;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        try {
            String back = Jsoup.connect(url).data(params).ignoreContentType(true).headers(headers).post().text();
            return back;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
