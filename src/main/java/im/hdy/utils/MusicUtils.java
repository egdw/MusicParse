package im.hdy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.hdy.model.Music;

import java.util.HashMap;

/**
 * Created by hdy on 10/02/2018.
 * 用于音乐链接的获取和请求下载
 */
public class MusicUtils {

    public static Music get(Integer type, String id) {
        HashMap<String, String> params = new HashMap<String, String>();
        HashMap<String, String> headers = new HashMap<String, String>();
        String url = null;
        String body = null;
        Music music = null;
        switch (type) {
            case 0:
                //喜马拉雅
                url = "http://mobile.ximalaya.com/v1/track/ca/playpage/" + id;
                headers.put("referer", "http://www.ximalaya.com");
                body = RequestUtils.get(url, params, headers);
                music = getXiMaLaYa(body);
                break;
            case 1:
                //kg
                url = ("http://kg.qq.com/cgi/kg_ugc_getdetail");
                headers.put("referer", "http://kg.qq.com");
                params.put("format", "json");
                params.put("inCharset", "utf8");
                params.put("outCharset", "utf-8");
                params.put("shareid", id);
                params.put("v", "4");
                body = RequestUtils.get(url, params, headers);
                break;
            case 2:
                //qingting
                // url=("http://i.qingting.fm/wapi/channels/"+id + "/programs/" +id,("$songid","1"));
                headers.put("referer", "http://www.qingting.fm");
                body = RequestUtils.get(url, params, headers);
                break;
            case 3:
                //lizhi
                url = ("http://m.lizhi.fm/api/audios_with_radio");
                headers.put("referer", "http://m.lizhi.fm");
                params.put("ids", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 4:
                //migu
                url = ("http://m.10086.cn/migu/remoting/cms_detail_tag");
                headers.put("referer", "http://m.10086.cn");
                params.put("cid", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 5:
                //5singyc
                url = ("http://mobileapi.5sing.kugou.com/song/newget");
                headers.put("referer", "http://5sing.kugou.com/yc/" + id + ".html");
                params.put("songid", id);
                params.put("songtype", "yc");
                body = RequestUtils.get(url, params, headers);
                break;
            case 6:
                //5singfc
                url = ("http://mobileapi.5sing.kugou.com/song/newget");
                headers.put("referer", "http://5sing.kugou.com/fc/" + id + ".html");
                params.put("songid", id);
                params.put("songtype", "fc");
                body = RequestUtils.get(url, params, headers);
                break;
            case 7:
                //xiami
                url = ("http://www.xiami.com/song/playlist/id/" + id + "/type/0/cat/json");
                headers.put("referer", "http://www.xiami.com");
                body = RequestUtils.get(url, params, headers);
                break;
            case 8:
                //qq
                url = "http://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg";
                headers.put("referer", "http://m.y.qq.com");
                params.put("songmid", id);
                params.put("format", "json");
                body = RequestUtils.get(url, params, headers);
                break;
            case 9:
                //酷我
                url = "http://player.kuwo.cn/webmusic/st/getNewMuiseByRid";
                headers.put("referer", "http://player.kuwo.cn/webmusic/play");
                params.put("rid", "MUSIC_" + id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 10:
                //酷狗
                url = "http://m.kugou.com/app/i/getSongInfo.php";
                headers.put("referer", "http://m.kugou.com/play/info/" + id);
                params.put("cmd", "playInfo");
                params.put("hash", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 11:
                //百度
                url = "http://music.baidu.com/data/music/links";
                headers.put("referer", "music.baidu.com/song/" + id);
                params.put("songIds", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 12:
                //1ting
                url = "http://h5.1ting.com/touch/api/song";
                headers.put("referer", "http://h5.1ting.com/#/song/" + id);
                params.put("ids", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 13:
                //'netease',需要加密
//                url = "http://music.163.com/api/linux/forward";
//                params.put("s",text);
//                body = RequestUtils.post(url, params, headers);
                break;
            default:
                break;
        }
        return music;
    }

    /**
     * 解析喜马拉雅的json数据
     *
     * @param body json数据
     * @return 解析完成的对象
     */
    private static Music getXiMaLaYa(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        JSONObject object = parse.getJSONObject("trackInfo");
        String pic = object.getString("coverMiddle");
        String playUrl32 = object.getString("playUrl32");
        String title = object.getString("title");
        String id = object.getString("trackId");
        String author = object.getString("categoryName");
        Music music = new Music();
        music.setUrl(playUrl32);
        music.setType(0);
        music.setTitle(title);
        music.setPic(pic);
        music.setAuthor(author);
        return music;
    }

//    private static Music get

}
