package im.hdy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import im.hdy.model.Music;

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
        JSONObject object = null;
        JSONArray jsonArray = null;
        Iterator<Object> iterator = null;
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("track").getJSONArray("docs");
                iterator = jsonArray.iterator();
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
                params.put("keyword", text);
                params.put("page", String.valueOf(page));
                params.put("pagesize", String.valueOf(limit));
                url = "http://mobilecdn.kugou.com/api/v3/search/song";
                body = RequestUtils.get(url, params);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONArray("info");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singername"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("hash"));
                    music.setTitle(next.getString("filename"));
                    music.setType(1);
                    music.setUrl(null);
                    musics.add(music);
                }
                System.out.println(musics);
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
                object = (JSONObject) JSON.parse(body);
                JSONObject o = (JSONObject) object.getJSONObject("data").getJSONArray("data").get(0);
                jsonArray = o.getJSONObject("doclist").getJSONArray("docs");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("parent_name"));
                    //搜索的时候没有图片- -
                    music.setPic(next.getString("cover"));
                    music.setSongid(next.getString("id"));
                    music.setTitle(next.getString("title"));
                    music.setType(2);
                    music.setUrl(null);
                    musics.add(music);
                }
                System.out.println(musics);
                break;
            case 3:
                //lizhi
                url = "http://m.lizhi.fm/api/search_audio/" + text + "/" + String.valueOf(page);
                headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                body = RequestUtils.get(url, params, headers);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("audio").getJSONArray("data");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    next = next.getJSONObject("audio");
                    Music music = new Music();
                    //作者
                    music.setAuthor(null);
                    //搜索的时候没有图片- -
                    //图片
                    music.setPic(null);
                    //id
                    music.setSongid(next.getString("id"));
                    //标题
                    music.setTitle(next.getString("name"));
                    //类型
                    music.setType(3);
                    //音乐地址
                    music.setUrl(next.getString("fixedHighPlayUrl"));
                    musics.add(music);
                }
                System.out.println(musics);
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONArray("musics");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singerName"));
                    //搜索的时候没有图片- -
                    music.setPic(next.getString("cover"));
                    music.setSongid(next.getString("id"));
                    music.setTitle(next.getString("title"));
                    music.setLrc(next.getString("lyrics"));
                    music.setType(4);
                    music.setUrl(next.getString("mp3"));
                    musics.add(music);
                }
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONArray("songArray");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singer"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("songId"));
                    music.setTitle(next.getString("songName"));
                    music.setLrc(null);
                    music.setType(5);
                    music.setUrl(null);
                    musics.add(music);
                }
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONArray("songArray");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singer"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("songId"));
                    music.setTitle(next.getString("songName"));
                    music.setLrc(null);
                    music.setType(6);
                    music.setUrl(null);
                    musics.add(music);
                }
                break;
            case 7:
                //xiami
                url = "http://api.xiami.com/web";
                headers.put("referer", "http://m.xiami.com");
                headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
                params.put("key", text);
                params.put("app_key", "1");
                params.put("r", "search/songs");
                params.put("v", "2.0");
                params.put("page", String.valueOf(page));
                params.put("limit", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONArray("songs");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("artist_name"));
                    //搜索的时候没有图片- -
                    music.setPic(next.getString("listen_file"));
                    music.setSongid(next.getString("song_id"));
                    music.setTitle(next.getString("song_name"));
                    music.setLrc(next.getString("lyric"));
                    music.setType(7);
                    music.setUrl(next.getString("listen_file"));
                    musics.add(music);
                }
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONObject("song").getJSONArray("list");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    JSONObject singer = (JSONObject) next.getJSONArray("singer").get(0);
                    music.setAuthor(singer.getString("name"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("songid"));
                    music.setTitle(next.getString("songname"));
                    music.setLrc(next.getString("lyric"));
                    music.setType(8);
                    music.setUrl(null);
                    musics.add(music);
                }
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONArray("abslist");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("ARTIST"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("MUSICRID"));
                    music.setTitle(next.getString("SONGNAME"));
                    music.setLrc(null);
                    music.setType(9);
                    music.setUrl(null);
                    musics.add(music);
                }
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
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("data").getJSONArray("info");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singername"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("hash"));
                    music.setTitle(next.getString("songname"));
                    music.setLrc(null);
                    music.setType(10);
                    music.setUrl(null);
                    musics.add(music);
                }
                break;
            case 11:
                //百度
                url = "http://musicapi.qianqian.com/v1/restserver/ting";
                headers.put("referer", "http://music.baidu.com/");
                params.put("method", "baidu.ting.search.common");
                params.put("format", "json");
                params.put("query", text);
                params.put("page_no", String.valueOf(page));
                params.put("page_size", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONArray("song_list");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("author"));
                    //搜索的时候没有图片- -
                    music.setPic(null);
                    music.setSongid(next.getString("song_id"));
                    music.setTitle(next.getString("title"));
                    music.setLrc("http://music.baidu.com" + next.getString("lrclink"));
                    music.setType(11);
                    music.setUrl(null);
                    musics.add(music);
                }
                break;
            case 12:
                //1ting
                url = "http://so.1ting.com/song/json";
                headers.put("referer", "http://h5.1ting.com/");
                params.put("q", text);
                params.put("page", String.valueOf(page));
                params.put("size", String.valueOf(limit));
                body = RequestUtils.get(url, params, headers);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONArray("results");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    music.setAuthor(next.getString("singer_name"));
                    //搜索的时候没有图片- -
                    music.setPic(next.getString("album_cover"));
                    music.setSongid(next.getString("song_id"));
                    music.setTitle(next.getString("song_name"));
                    music.setLrc(null);
                    music.setType(12);
                    music.setUrl(null);
                    musics.add(music);
                }
                break;
            case 13:
                //'netease',需要加密
                url = "http://music.163.com/api/search/pc?s=" + text + "&offset=" + String.valueOf(Math.abs(1 * page - 10)) + "&limit=" + String.valueOf(limit) + "&type=1";
                headers.put("Cookie", "appver=1.5.0.75771");
                headers.put("Referer", "http://music.163.com/");
                body = RequestUtils.post(url, params, headers);
                object = (JSONObject) JSON.parse(body);
                jsonArray = object.getJSONObject("resultF").getJSONArray("songs");
                iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject next = (JSONObject) iterator.next();
                    Music music = new Music();
                    JSONObject singer = (JSONObject) next.getJSONArray("artists").get(0);
                    JSONObject album = next.getJSONObject("album");
                    music.setAuthor(singer.getString("name"));
                    //搜索的时候没有图片- -
                    music.setPic(album.getString("picUrl"));
                    music.setSongid(next.getString("id"));
                    music.setTitle(next.getString("name"));
                    music.setLrc(null);
                    music.setType(13);
                    music.setUrl(null);
                    musics.add(music);
                }
                break;
            default:
                break;
        }
        System.out.println(musics);
        return body;
    }
}
