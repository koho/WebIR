package com.flyingleaf.myself;

/**
 * Created by Koho on 2016/4/8.
 */
public class URLOptions {

    private String host;
    private String path;
    private int port;
    private String charset;
    private String method;

    public URLOptions(String host, String path, int port, String charset, String method) {
        this.host = host;
        this.path = path;
        this.port = port;
        this.charset = charset;
        if (method == null)
            this.method = "GET";
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public int getPort() {
        return port;
    }

    public String getCharset() {
        return charset;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static class Builder {

        private String host;
        private String path;
        private int port;
        private String charset;
        private String method;

        public Builder() {

        }

        public URLOptions build() {
            return new URLOptions(getHost(),
                    getPath(),
                    getPort(),
                    getCharset(),
                    getMethod());
        }

        public String getMethod() {
            return method;
        }

        public URLOptions.Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public String getHost() {
            return host;
        }

        public URLOptions.Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public String getPath() {
            return path;
        }

        public URLOptions.Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public int getPort() {
            return port;
        }

        public URLOptions.Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public String getCharset() {
            return charset;
        }

        public URLOptions.Builder setCharset(String charset) {
            this.charset = charset;
            return this;
        }
    }
}
