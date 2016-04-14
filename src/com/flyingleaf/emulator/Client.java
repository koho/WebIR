package com.flyingleaf.emulator;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Koho on 2016/4/13.
 */
public class Client {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    protected static final String DEFAULT_URL_ENCODING = "UTF-8";

    private HttpURLConnection connection;
    private String url;
    private String urlEncoding;
    private List<String> paramList;
    private String charset;

    public Client(String url) {
        this.url = url;
        paramList = new ArrayList<>();
        urlEncoding = DEFAULT_URL_ENCODING;
    }

    protected void setCharset(String charset) {
        this.charset = charset;
    }

    protected String getCharset() {
        return this.charset;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    protected String getUrl() {
        return this.url;
    }

    protected int sendPost() throws IOException {
        if (url == null)
            throw new IOException("URL can not be null");
        URL postUrl = new URL(this.url);
        connection = (HttpURLConnection) postUrl.openConnection();
        connection.setRequestMethod(METHOD_POST);
        getRequestProperty(connection);

        return 0;
    }

    protected String getPage() throws IOException{
        if (url == null)
            throw new IOException("URL can not be null");
        URL getUrl = new URL(this.url);
        if (getQueryParameters() != null) {
            getUrl = new URL(this.url + "?" + getQueryParameters());
        }
        connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod(METHOD_GET);
        getRequestProperty(connection);
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP ERR");
        }
        InputStream inputStream = connection.getInputStream();
        BufferedReader in;
        if (getCharset() != null) {
            in = new BufferedReader(new InputStreamReader(
                    inputStream, charset
            ));
        } else {
            in = new BufferedReader(new InputStreamReader(
                    inputStream
            ));
        }
        String input;
        StringBuilder response = new StringBuilder();

        while ((input = in.readLine()) != null) {
            response.append(input);
        }
        in.close();
        return response.toString();
    }

    protected void getRequestProperty(HttpURLConnection connection) {
        connection.setRequestProperty("Connection", "close");
    }

    protected String getQueryParameters() {
        StringBuilder result = new StringBuilder();
        if (paramList == null || paramList.isEmpty())
            return null;
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&").append(param);
            }
        }
        return result.toString();
    }

    protected void addParameter(String key, String value) throws UnsupportedEncodingException {
        if (paramList == null)
            paramList = new ArrayList<>();
        paramList.add(key + "=" + URLEncoder.encode(value, this.urlEncoding));
    }

    protected void clearParameters() {
        if (paramList != null)
            paramList.clear();
    }

    protected void setUrlEncoding(String encoding) {
        this.urlEncoding = encoding;
    }

}
