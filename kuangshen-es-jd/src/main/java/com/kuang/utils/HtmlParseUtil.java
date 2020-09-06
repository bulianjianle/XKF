package com.kuang.utils;

import com.kuang.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * HtmlParseUtil
 *
 * @author danggui
 * @date 2020/9/6 18:06
 */

@Component
public class HtmlParseUtil {

    public static void main(String[] args) throws Exception {
        parseJD("深入");
    }
    public static List<Content> parseJD(String keywords)throws Exception{
        String url="https://search.jd.com/Search?keyword="+keywords;
        // 解析网络,(jsoup返回document就是浏览器document对象)
        Document document = Jsoup.parse(new URL(url),30000);
        // 所有在js能用的方法在这都能用
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li标签
        Elements elements=element.getElementsByTag("li");
        List<Content> contentList=new ArrayList<>();
        for (Element el:elements){
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content build = Content.builder().img(img).price(price).title(title).build();
            contentList.add(build);
        }
    return contentList;


    }



}
