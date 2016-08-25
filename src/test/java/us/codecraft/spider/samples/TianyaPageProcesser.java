package us.codecraft.spider.samples;

import java.util.List;

import us.codecraft.spider.Page;
import us.codecraft.spider.Site;
import us.codecraft.spider.processor.PageProcessor;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午1:48
 */
public class TianyaPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> strings = page.getHtml().rs("<a[^<>]*href=[\"']{1}(/post-free.*?\\.shtml)[\"']{1}").toStrings();
        page.addTargetRequests(strings);
        page.putField("title", page.getHtml().x("//div[@id='post_head']//span[@class='s_title']//b"));
        page.putField("body",page.getHtml().sc());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("http://bbs.tianya.cn/").setStartUrl("http://bbs.tianya.cn/");  //To change body of implemented methods use File | Settings | File Templates.
    }
}
