package com.flyingleaf;

import com.flyingleaf.myself.FetchHtmlBySocket;
import com.flyingleaf.myself.URLOptions;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WebHttpClient client = new WebHttpClient("http://home.baidu.com/2016-03-01/1462366874.html");
        try {
//            client.setEncoding("gb2312");
//            System.out.println(client.getPageContent());
//            WebCrawler crawler = new WebCrawler("I:\\workshop\\ch2\\htmlsrc.html");
//            crawler.saveFile("http://home.baidu.com/2016-03-01/1462366874.html", "GB2312");
            FetchHtmlBySocket htmlBySocket = new FetchHtmlBySocket(
                    new URLOptions.Builder().setHost("home.baidu.com")
                        .setPath("/2016-03-01/1462366874.html").setPort(80)
                        .setCharset("gb2312").build()
            );
            htmlBySocket.getRequestOptions().add("Connection", "close")
                    .add("User-Agent", "Mozilla/5.0");
            System.out.println(htmlBySocket.getPageContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
