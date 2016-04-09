package com.flyingleaf;

import java.io.*;
import java.net.Socket;
import java.net.URL;

/**
 * Created by Koho on 2016/4/8.
 */
public class WebHttpClient {

    private String mUrl;
    protected Socket mSocket;
    private String encoding;

    public WebHttpClient(String url) {
        this.mUrl = url;
    }

    public WebHttpClient() { }

    public String getPageContent(String url) throws IOException {
        if (url == null)
            return null;
        URL path = new URL(url);
        mSocket = new Socket(path.getHost(), 80);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(mSocket.getOutputStream())
        );
        String builder = "GET " + path.getPath() + " " + "HTTP/1.1" + "\r\n" +
                "Host: " + path.getHost() + "\r\n" +
                "Connection: " + "close" + "\r\n" + "\r\n";
        System.out.println(builder);
        writer.write(builder);
        writer.flush();
        BufferedReader reader;
        if (encoding != null) {
            reader = new BufferedReader(
                    new InputStreamReader(mSocket.getInputStream(), encoding)
            );
        } else {
            reader = new BufferedReader(
                    new InputStreamReader(mSocket.getInputStream())
            );
        }
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        writer.close();
        reader.close();
        return result.toString();
    }

    public String getPageContent() throws IOException {
        return getPageContent(this.mUrl);
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
