package im.hdy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.hdy.model.Music;
import org.jdom.Document;
import org.xml.sax.SAXException;

import javax.sound.midi.Soundbank;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Random;

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
                url = ("http://m.kugou.com/app/i/getSongInfo.php");
                headers.put("referer", "http://m.kugou.com/play/info/" + id);
                params.put("cmd", "playInfo");
                params.put("hash", id);
                body = RequestUtils.get(url, params, headers);
                music = getKuGou(body);
                break;
            case 2:
                //qingting
                String[] split = id.split(" ");
                System.out.println(id);
                System.out.println(split[0] + " " + split[1]);
                url = "http://i.qingting.fm/wapi/channels/" + split[0] + "/programs/" + split[1];
                System.out.println(url);
                headers.put("referer", "http://www.qingting.fm");
                body = RequestUtils.get(url, params, headers);
                music = getQingTing(body);
                break;
            case 3:
                //lizhi(url自带音乐)
                System.out.println(id);
                url = ("http://m.lizhi.fm/api/audios_with_radio");
                headers.put("referer", "http://m.lizhi.fm");
                params.put("ids", id);
                body = RequestUtils.get(url, params, headers);
                music = getLizZhi(body);
                break;
            case 4:
                //migu(url自带音乐)
                url = ("http://m.10086.cn/migu/remoting/cms_detail_tag");
                headers.put("referer", "http://m.10086.cn");
                params.put("cid", id);
                body = RequestUtils.get(url, params, headers);
                music = getMiGu(body);
                break;
            case 5:
                //5singyc
                url = ("http://mobileapi.5sing.kugou.com/song/newget");
                headers.put("referer", "http://5sing.kugou.com/yc/" + id + ".html");
                params.put("songid", id);
                params.put("songtype", "yc");
                body = RequestUtils.get(url, params, headers);
                music = singyc(body);
                break;
            case 6:
                //5singfc
                System.out.println(id);
                url = ("http://mobileapi.5sing.kugou.com/song/newget");
                headers.put("referer", "http://5sing.kugou.com/fc/" + id + ".html");
                params.put("songid", id);
                params.put("songtype", "fc");
                body = RequestUtils.get(url, params, headers);
                System.out.println(body);
                music = singfc(body);
                break;
            case 7:
                //xiami //这里需要解密
                System.out.println(id);
                url = ("http://www.xiami.com/song/playlist/id/" + id + "/type/0/cat/json");
                System.out.println(url);
                headers.put("referer", "http://www.xiami.com");
                body = RequestUtils.get(url, params, headers);
                music = xiami(body);
                break;
            case 8:
                //qq
                System.out.println(id);
//                try {
//                    String vKey = getVKey(id);
//                    StringBuilder sb = new StringBuilder("http://dl.stream.qqmusic.qq.com/").append(fileName).append("?")
//                            .append("vkey=").append(vKey).append("&guid=").append(guid);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                url = "http://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg";
                headers.put("referer", "http://m.y.qq.com");
                params.put("songmid", id);
                params.put("format", "json");
                body = RequestUtils.get(url, params, headers);
                qq(id);
                break;
            case 9:
                //酷我
                System.out.println(id);
                url = "http://player.kuwo.cn/webmusic/st/getNewMuiseByRid";
                headers.put("referer", "http://player.kuwo.cn/webmusic/play");
//                params.put("rid", "MUSIC_" + id);
                params.put("rid", id);
                body = RequestUtils.get(url, params, headers);
                try {
                    music = KuWo(body);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
                break;
            case 10:
                //酷狗
                System.out.println(id);
                url = "http://m.kugou.com/app/i/getSongInfo.php";
                headers.put("referer", "http://m.kugou.com/play/info/" + id);
                params.put("cmd", "playInfo");
                params.put("hash", id);
                body = RequestUtils.get(url, params, headers);
                music = kuogou(body);
                break;
            case 11:
                //百度
                System.out.println(id);
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
        System.out.println(body);
        return music;
    }

    private static Music getMiGu(String body) {

        return null;
    }

    private static Music getLizZhi(String body) {
        return null;
    }

    private static Music singyc(String body) {
        //没有图片
        JSONObject parse = (JSONObject) JSON.parse(body);
        JSONObject object = parse.getJSONObject("data");
        String playUrl32 = object.getString("KL");
        String id = object.getString("ID");
        String name = object.getString("SN");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setAuthor(name);
        music.setType(5);
        return music;
    }

    private static Music singfc(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        JSONObject object = parse.getJSONObject("data");
        String playUrl32 = object.getString("KL");
        String id = object.getString("ID");
        String name = object.getString("SN");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setAuthor(name);
        music.setType(6);
        return music;
    }

    private static Music xiami(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        JSONObject object = (JSONObject) parse.getJSONObject("data").getJSONArray("trackList").get(0);
        String pic = object.getString("pic");
        //这里需要解密
        String playUrl32 = object.getString("location");
        String title = object.getString("songName");
        String id = object.getString("songId");
        String author = object.getString("singers");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setType(0);
        music.setTitle(title);
        music.setLrc(object.getString("lyric_url"));
        music.setPic(pic);
        music.setAuthor(author);
        return music;
    }

    public static String qq(String mediaMid) {
        StringBuilder sb = new StringBuilder("https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?");
        String fileName = "C400" + mediaMid + ".m4a";
        Random random = new Random();
        long guid = (Long.valueOf(random.nextLong() * 2147483647) * (Long.valueOf((System.currentTimeMillis() * 1000)))
                % 10000000000l);
        sb.append("format=json&platform=yqq&cid=205361747&songmid=").append(mediaMid).append("&filename=")
                .append(fileName).append("&guid=").append(guid);
        String s = RequestUtils.get(sb.toString(), new HashMap<String, String>());
        System.out.println(s);
        JSONObject object = JSON.parseObject(s);
        JSONObject object2 = (JSONObject) object.getJSONObject("data").getJSONArray("items").get(0);
        String vkey = (String) object2.get("vkey");
        StringBuilder sb2 = new StringBuilder("http://dl.stream.qqmusic.qq.com/").append(fileName).append("?")
                .append("vkey=").append(vkey).append("&guid=").append(guid);
        //String requesst = openConnection(musicUrl, "get",
        //"pgv_pvi=6725760000;pgv_si=s4324782080;pgv_pvid=" + guid + ";qqmusic_fromtag=66", null, true);
        System.out.println(sb2.toString());
        return null;
    }

    private static Music KuWo(String body) throws ParserConfigurationException, IOException, SAXException {
//        String[] split = body.split(" ");
//        for(String s:split){
//            System.out.println(s);
////        String xml = parseXml("<music_id>", body)
//        };
//        String pic = object.getString("coverMiddle");
//        String playUrl32 = object.getString("playUrl32");
//        String title = object.getString("title");
//        String id = object.getString("trackId");
//        String author = object.getString("categoryName");
//        Music music = new Music();
//        music.setSongid(split[1]);
//        music.setUrl();
//        music.setType(9);
//        music.setTitle(split[3]);
//        music.setPic(split[]);
//        music.setAuthor(author);
//        return music;
        return null;
    }

    private static Music kuogou(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        String pic = parse.getString("imgUrl");
        String playUrl32 = parse.getString("url");
        String title = parse.getString("songName");
        String id = parse.getString("hash");
        String author = parse.getString("choricSinger");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setType(10);
        music.setTitle(title);
        music.setPic(pic);
        music.setAuthor(author);
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
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setType(0);
        music.setTitle(title);
        music.setPic(pic);
        music.setAuthor(author);
        return music;
    }

    /**
     * 解析酷狗音乐的json数据
     *
     * @param body json数据
     * @return 解析完成的对象
     */
    private static Music getKuGou(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        //图片
        String pic = parse.getString("album_img");
        //音乐地址
        String playUrl32 = parse.getString("url");
        //标题
        String title = parse.getString("songName");
        //id
        String id = parse.getString("hash");
        //作者
        String author = parse.getString("singerName");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setType(1);
        music.setTitle(title);
        music.setPic(pic);
        music.setAuthor(author);
        return music;
    }

    /**
     * 解析蜻蜓FM的json数据
     *
     * @param body json数据
     * @return 解析完成的对象
     */
    private static Music getQingTing(String body) {
        JSONObject parse = (JSONObject) JSON.parse(body);
        JSONObject object = parse.getJSONObject("data");
        //图片
        String pic = object.getString("img_url");
        //音乐地址
        String playUrl32 = "http://od.qingting.fm/" + object.getString("file_path");
        //标题
        String title = object.getString("name");
        //id
        String id = object.getString("id");
        //作者
        String author = object.getString("singerName");
        Music music = new Music();
        music.setSongid(id);
        music.setUrl(playUrl32);
        music.setType(1);
        music.setTitle(title);
        music.setPic(pic);
        music.setAuthor(author);
        return music;
    }
//    private static Music get

    /**
     * 获取vkey值
     *
     * @throws IOException
     */
    public static String getVKey(String mediaMid) throws IOException {
        StringBuilder sb = new StringBuilder("https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg?");
        String fileName = "C400" + mediaMid + ".m4a";
        Random random = new Random();
        long guid = (Long.valueOf(random.nextLong() * 2147483647) * (Long.valueOf((System.currentTimeMillis() * 1000)))
                % 10000000000l);
        sb.append("format=json&platform=yqq&cid=205361747&songmid=").append(mediaMid).append("&filename=")
                .append(fileName).append("&guid=").append(guid);
        String s = RequestUtils.get(sb.toString(), new HashMap<String, String>());
        System.out.println(s);
        JSONObject object = JSON.parseObject(s);
        JSONObject object2 = (JSONObject) object.getJSONObject("data").getJSONArray("items").get(0);
        String vkey = (String) object2.get("vkey");
        StringBuilder sb2 = new StringBuilder("http://dl.stream.qqmusic.qq.com/").append(fileName).append("?")
                .append("vkey=").append(vkey).append("&guid=").append(guid);
        return sb2.toString();
//        JSONObject object = JSON.parseObject(request);
//        JSONObject object2 = (JSONObject) object.getJSONObject("data").getJSONArray("items").get(0);
//        vkey = (String) object2.get("vkey");
    }

    private static String parseXml(String name, String body) {
        System.out.println(body);
        int first = body.indexOf(name);
        int last = body.indexOf("</" + name.substring(1));
        System.out.println(first);
        System.out.println(last);
        return body.substring(first + name.length() - 1, last);
    }
}
