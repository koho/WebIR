package com.flyingleaf.myself;

import com.flyingleaf.WebHttpClient;

import java.io.*;
import java.net.Socket;

/**
 * Created by Koho on 2016/4/8.
 */
public class FetchHtmlBySocket extends WebHttpClient {

    private URLOptions mUrlOptions;
    private RequestOptions mRequestOptions;

    public FetchHtmlBySocket(URLOptions urlOptions) {
        this(urlOptions, null);
    }

    public FetchHtmlBySocket(URLOptions urlOptions, RequestOptions requestOptions) {
        this.mUrlOptions = urlOptions;
        this.mRequestOptions = requestOptions;
        if (this.mRequestOptions == null) {
            this.mRequestOptions = new RequestOptions();
            this.mRequestOptions.add("Host", this.mUrlOptions.getHost());
        }
    }

    @Override
    public String getPageContent() throws IOException {
        if (mUrlOptions == null)
            return null;
        if (mUrlOptions.getHost() == null) {
            throw new IOException("host name can not be null");
        }
        if (mUrlOptions.getPort() <= 0 || mUrlOptions.getPort() > 65535) {
            throw new IOException("port is out of range");
        }
        mSocket = new Socket(mUrlOptions.getHost(), mUrlOptions.getPort());
        setEncoding(mUrlOptions.getCharset());
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(mSocket.getOutputStream())
        );
        StringBuilder builder = new StringBuilder();
        builder.append(mUrlOptions.getMethod()).append(" ");
        builder.append(mUrlOptions.getPath()).append(" ");
        builder.append("HTTP/1.1").append("\r\n");
        if (mRequestOptions != null) {
            for (String req : mRequestOptions.getRequestList()) {
                builder.append(req).append("\r\n");
            }
        }
        builder.append("\r\n");
        String request = builder.toString();
        System.out.println(request);
        writer.write(request);
        writer.flush();
        BufferedReader reader;
        if (getEncoding() != null) {
            reader = new BufferedReader(
                    new InputStreamReader(mSocket.getInputStream(), getEncoding())
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

    @Override
    public String getPageContent(String url) throws IOException {
        return getPageContent();
    }

    public void setRequestOptions(RequestOptions options) {
        this.mRequestOptions = options;
    }

    public RequestOptions getRequestOptions() {
        return this.mRequestOptions;
    }

    public void setUrlOptions(URLOptions options) {
        this.mUrlOptions = options;
    }
}
