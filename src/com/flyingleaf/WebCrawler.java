package com.flyingleaf;

import java.io.*;

/**
 * Created by Koho on 2016/4/8.
 */
public class WebCrawler {

    private String mPath;
    private WebHttpClient mClient;
    private String mName;

    public WebCrawler(String path) {
        this.mPath = path;
        this.mClient = new WebHttpClient();
    }

    public WebCrawler() { }

    public void saveFile(String url, String encoding, String path) throws IOException {
        if (path == null || url == null)
            return;
        mClient.setUrl(url);
        mClient.setEncoding(encoding);
        File file = new File(path);
        if (!file.exists()) {
            mName = file.getName();
            createFile(file);
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(mClient.getPageContent());
        writer.flush();
        writer.close();
    }

    public void saveFile(String url) throws IOException {
        saveFile(url, null, this.mPath);
    }

    public void saveFile(String url, String encoding) throws IOException {
        saveFile(url, encoding, this.mPath);
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    private void createFile(File file) throws IOException {
        if (!file.exists()) {
            createFile(file.getParentFile());
            if (file.getName().equals(mName))
                file.createNewFile();
            else
                file.mkdir();
        }
    }
}
