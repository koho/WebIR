package com.flyingleaf.emulator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * Created by Koho on 2016/4/13.
 */
public class CtripSearch extends Client {

    public static final String CTRIP_SEARCH_URL = "http://m.ctrip.com/restapi/h5api/searchapp/search";

    public CtripSearch() {
        super(CTRIP_SEARCH_URL);
    }

    @Override
    protected void getRequestProperty(HttpURLConnection connection) {
        super.getRequestProperty(connection);
    }

    public String searchAttractionIntroduction(String keyword) throws IOException {
        setQueryString(keyword);
        String attraction = getAttractionURL(getPage());
        clearParameters();
        setUrl(attraction);
        Document document = Jsoup.parse(getPage());
        Elements elements = document.select("div.detailcon.detailbox_dashed>div.toggle_l>div.text_style");
        for (Element element : elements) {
            if (element.hasAttr("itemprop")) {
                Element description =
                        element.getElementsByAttributeValue("itemprop", "description").first();
                if (description != null)
                    return description.text();
            }
        }
        return null;
    }

    private String getAttractionURL(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            return object.getString("url");
        }
        return null;
    }

    private void setQueryString(String keyword) {
        try {
            addParameter("action", "autocomplete");
            addParameter("source", "globalonline");
            addParameter("keyword", keyword);
            addParameter("t", "1460535924743");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
