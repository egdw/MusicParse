package im.hdy.utils;

import java.util.HashMap;

/**
 * Created by hdy on 10/02/2018.
 * 用于音乐的歌词获取
 */
public class LrcUtils {

    /**
     * @param type 来源歌曲厂商
     * @param id   歌曲的id
     */
    public static String getLrc(Integer type, String id) {
        HashMap<String, String> params = new HashMap<String, String>();
        HashMap<String, String> headers = new HashMap<String, String>();
        String url = null;
        String body = null;
        switch (type) {
            case 0:
                //喜马拉雅(无歌词)
                break;
            case 1:
                //kg
                url = ("http://kg.qq.com/cgi/fcg_lyric");
                headers.put("referer", "http://kg.qq.com");
                params.put("format", "json");
                params.put("inCharset", "utf8");
                params.put("outCharset", "utf-8");
                params.put("ksongmid", id);
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
                url = (id);
                headers.put("referer", "http://www.xiami.com");
                body = RequestUtils.get(url, params, headers);
                break;
            case 8:
                //qq
                url = "http://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric.fcg";
                headers.put("referer", "http://m.y.qq.com");
                params.put("songmid", id);
                params.put("format", "json");
                params.put("nobase64", "1");
                params.put("songtype", "0");
                params.put("callback", "c");
                body = RequestUtils.get(url, params, headers);
                break;
            case 9:
                //酷我
                url = "http://m.kuwo.cn/newh5/singles/songinfoandlrc";
                headers.put("referer", "http://m.kuwo.cn/yinyue/" + id);
                params.put("musicId", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 10:
                //酷狗
                url = "http://m.kugou.com/app/i/krc.php";
                headers.put("referer", "http://m.kugou.com/play/info/" + id);
                params.put("cmd", "100");
                params.put("timelength", "999999");
                params.put("hash", id);
                body = RequestUtils.get(url, params, headers);
                break;
            case 11:
                //百度
                url = "http://musicapi.qianqian.com/v1/restserver/ting";
                headers.put("referer", "http://music.baidu.com/song/" + id);
                params.put("method", "baidu.ting.song.lry");
                params.put("songid", id);
                params.put("format", "json");
                body = RequestUtils.get(url, params, headers);
                break;
            case 12:
                //1ting
                url = "http://www.1ting.com/api/geci/lrc/" + id;
                headers.put("referer", "http://www.1ting.com/geci" + id + ".html");
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
        return body;
    }
}
