package cm.inv.com.crawler.module.reptile.jianshu;

/**
 * Created by zhangtao107@126.com on 2016/9/9.
 */
public class Note {
    private static final long serialVersionUID = -5696033709028657709L;
    /**
     * id : 5660816
     * abbr : 前言   iOS工程师一直都是那么的高逼格，用的是Mac电脑，耍的是iPhone手机，哇咔咔~~  但是，作为一名iOS开发工程师，我们除了高逼格外，还必须是全能的。你不会点UI设计、不会点后台语言、不会点安卓开发...那都是不可能的事情。  好了，今天咱们不是来吹牛逼的。而是给大家添虎翼的。做了iOS开发也有一年多了，懂得了工欲善其事，必先利其器的道理。Mac上有很多好用的软件，有的能提升我们的开发效率，有的能提升我们的工作效率。今天我就来介绍一些Mac上对我们开发有帮助的软件。 这里介绍的软件都是我日常使用的，在此记录并分享出来，如有更好用的软件也可以在评论区介绍给我，感激不尽。 1...
     * slug : fbd19f724cf1
     * url : http://www.jianshu.com/p/fbd19f724cf1
     * image_url : http://cwb.assets.jianshu.io/notes/images/5660816/weibo/image_f57c31da5fdc.jpg
     * wordage : 2845
     * has_further_readings : false
     * views_count : 4445
     * likes_count : 232
     * comments_count : 54
     * rewards_total_count : 0
     */

    private int id;
    private String abbr;
    private String slug;
    private String url;
    private String image_url;
    private int wordage;
    private boolean has_further_readings;
    private int views_count;
    private int likes_count;
    private int comments_count;
    private int rewards_total_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getWordage() {
        return wordage;
    }

    public void setWordage(int wordage) {
        this.wordage = wordage;
    }

    public boolean isHas_further_readings() {
        return has_further_readings;
    }

    public void setHas_further_readings(boolean has_further_readings) {
        this.has_further_readings = has_further_readings;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRewards_total_count() {
        return rewards_total_count;
    }

    public void setRewards_total_count(int rewards_total_count) {
        this.rewards_total_count = rewards_total_count;
    }
}
