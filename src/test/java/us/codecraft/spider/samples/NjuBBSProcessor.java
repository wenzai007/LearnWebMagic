package us.codecraft.spider.samples;

import java.util.List;

import us.codecraft.spider.Page;
import us.codecraft.spider.Site;
import us.codecraft.spider.processor.PageProcessor;

/**
 * User: cairne
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class NjuBBSProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().rs("<a[^<>]*href=(bbstcon\\?board=Pictures&file=[^>]*)").toStrings();
        page.addTargetRequests(requests);
        page.putField("title",page.getHtml().x("//div[@id='content']//h2/a"));
        page.putField("content",page.getHtml().sc());
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("bbs.nju.edu.cn").setStartUrl("http://bbs.nju.edu.cn/board?board=Pictures").
                setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    }
}
