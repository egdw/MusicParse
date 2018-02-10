package im.hdy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import im.hdy.model.Music;
import im.hdy.utils.RequestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
        ArrayList<Music> musics = new ArrayList<Music>();
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
                JSONObject object = (JSONObject) JSON.parse(body);
                JSONArray jsonArray = object.getJSONObject("track").getJSONArray("docs");
                Iterator<Object> iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    String id = next.getString("id");
                    String play_path_32 = next.getString("play_path_32");
                    String title = next.getString("title");
                    String album_cover_path = next.getString("album_cover_path");
                    String nickname = next.getString("nickname");
                    Music music = new Music();
                    music.setAuthor(nickname);
                    music.setPic(album_cover_path);
                    music.setSongid(id);
                    music.setTitle(title);
                    music.setType(0);
                    music.setUrl(play_path_32);
                    musics.add(music);
                }
                System.out.println(musics);
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
                params.put("k", text);
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
                url = "http://m.lizhi.fm/api/search_audio/" + text + "/" + String.valueOf(page);
                headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                body = RequestUtils.get(url, params, headers);
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
                body = RequestUtils.get(url, params, headers);
                break;
            case 8:
                //qq
                url = "http://c.y.qq.com/soso/fcgi-bin/search_for_qq_cp";
                headers.put("referer", "http://m.y.qq.com");
                params.put("w", text);
                params.put("p", String.valueOf(page));
                params.put("n", String.valueOf(limit));
                params.put("format", "json");
                body = RequestUtils.get(url, params, headers);
                break;
            case 9:
                //酷我
                url = "http://search.kuwo.cn/r.s";
                headers.put("referer", "http://player.kuwo.cn/webmusic/play");
                params.put("pn", String.valueOf(page - 1));
                params.put("ft", "music");
                params.put("all", text);
                params.put("rn", String.valueOf(limit));
                params.put("itemset", "web_2013");
                params.put("rformat", "json");
                params.put("encoding", "utf8");
                body = RequestUtils.get(url, params, headers);
                break;
            case 10:
                //酷狗
                url = "http://mobilecdn.kugou.com/api/v3/search/song";
                headers.put("referer", "http://m.kugou.com/v2/static/html/search.html");
                params.put("format", "json");
                params.put("keyword", text);
                params.put("page", String.valueOf(page));
                params.put("pagesize", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                break;
            case 11:
                //百度
                url = "http://musicapi.qianqian.com/v1/restserver/ting";
                headers.put("method", "baidu.ting.search.common");
                headers.put("referer", "http://music.baidu.com/");
                params.put("format", "json");
                params.put("query", text);
                params.put("page_no", String.valueOf(page));
                params.put("page_size", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                break;
            case 12:
                //1ting
                url = "http://so.1ting.com/song/json";
                headers.put("referer", "http://h5.1ting.com/");
                params.put("q", text);
                params.put("page", String.valueOf(page));
                params.put("size", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                break;
            case 13:
                //'netease',需要加密
                url = "http://music.163.com/api/linux/forward";
                headers.put("referer", "http://music.163.com/");
//                params.put("method","POST");
                params.put("url", "http://music.163.com/api/cloudsearch/pc");
                params.put("s", text);
                params.put("type", String.valueOf(page + 1));
                params.put("offset", String.valueOf(page * 10 - 10));
                params.put("limit", String.valueOf(limit));
                body = RequestUtils.post(url, params, headers);
                break;
            default:
                break;
        }
        return body;
    }
}
