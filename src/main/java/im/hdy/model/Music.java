package im.hdy.model;

/**
 * Created by hdy on 10/02/2018.
 */
public class Music {
    private Integer type;
    private String link;
    private String songid;
    private String title;
    private String author;
    private String lrc;
    private String url;
    private String pic;

    public Music(Integer type, String link, String songid, String title, String author, String lrc, String url, String pic) {
        this.type = type;
        this.link = link;
        this.songid = songid;
        this.title = title;
        this.author = author;
        this.lrc = lrc;
        this.url = url;
        this.pic = pic;
    }

    public Music() {
    }

    public Music(String author) {
        this.author = author;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Music{" +
                "type=" + type +
                ", link='" + link + '\'' +
                ", songid='" + songid + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", lrc='" + lrc + '\'' +
                ", url='" + url + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
