package im.hdy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by hdy on 09/02/2018.
 * 音乐搜索工具类
 */
public class SearchUtils {

    /**
     * @param type  类型.比如网易云音乐等等
     * @param text  搜索的文本
     * @param page  当前页码
     * @param limit 检索数量限制
     */
    public static String search(Integer type, String text, Integer page, Integer limit) {
        HashMap<String, String> params = new HashMap<String, String>();
        HashMap<String, String> headers = new HashMap<String, String>();
        String url = null;
        String body = null;
        switch (type) {
            case 0:
                //喜马拉雅
                params.put("kw", text);
                params.put("core", "all");
                params.put("page", String.valueOf(page));
                params.put("rows", String.valueOf(limit));
                params.put("is_paid", "false");
                url = "http://search.ximalaya.com/front/v1";
                body = RequestUtils.get(url, params);
                break;
            case 1:
                //kg
                params.put("format", "json");
                params.put("type", "get_ugc");
                params.put("inCharset", "utf8");
                params.put("outCharset", "utf-8");
                params.put("share_uid", text);
                params.put("start", String.valueOf(page));
                params.put("num", String.valueOf(limit));
                url = "http://kg.qq.com/cgi/kg_ugc_get_homepage";
                body = RequestUtils.get(url, params);
                break;
            case 2:
                //qingting
                try {
                    params.put("k", URLEncoder.encode(text, "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                params.put("page", String.valueOf(page));
                params.put("pagesize", String.valueOf(limit));
                params.put("include", "program_ondemand");
                params.put("groups", "program_ondemand");
                headers.put("referer", "http://www.qingting.fm");
                url = "http://i.qingting.fm/wapi/search";
                body = RequestUtils.get(url, params, headers);
                break;
            case 3:
                //lizhi
                try {
                    url = "http://m.lizhi.fm/api/search_audio/" + URLEncoder.encode(text, "utf8") + "/" + String.valueOf(page);
                    headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                    body = RequestUtils.get(url, params, headers);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                //migu
                url = "http://m.10086.cn/migu/remoting/scr_search_tag";
                params.put("keyword", text);
                params.put("type", "2");
                params.put("pgc", String.valueOf(page));
                params.put("rows", String.valueOf(limit));
                headers.put("referer", "http://m.10086.cn");
                body = RequestUtils.get(url, params, headers);
                break;
            case 5:
                //5singfc
                url = "http://goapi.5sing.kugou.com/search/search";
                headers.put("referer", "http://5sing.kugou.com/");
                params.put("k", text);
                params.put("t", "0");
                params.put("filterType", "2");
                params.put("pn", String.valueOf(page));
                params.put("ps", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                break;
            case 6:
                //5singyc
                url = "http://goapi.5sing.kugou.com/search/search";
                headers.put("referer", "http://5sing.kugou.com/");
                params.put("k", text);
                params.put("t", "0");
                params.put("filterType", "1");
                params.put("pn", String.valueOf(page));
                params.put("ps", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                break;
            case 7:
                //xiami
                url = "http://api.xiami.com/web";
                headers.put("referer", "http://m.xiami.com");
                headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                try {
                    params.put("key", URLEncoder.encode(text, "utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                params.put("app_key", "1");
                params.put("r", "search/songs");
                params.put("v", "2.0");
                params.put("page", String.valueOf(page));
                params.put("limit", String.valueOf(limit));
                break;
            default:
                break;
        }
        return body;
    }
}
