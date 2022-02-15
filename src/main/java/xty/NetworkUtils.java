package xty;


import com.google.gson.Gson;
import xty.request.SlowTableRequest;
import xty.response.CardData;
import xty.response.RegionData;
import xty.response.SlowTable;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class NetworkUtils {
    public static final String URL_BASE = "https://wukong2.tingyun.com";
    public static final String URL_SLOW_TABLE = URL_BASE + "/app-api/adhoc/query?label=slowTableData";
    public static final String URL_REGION_DATA = URL_BASE + "/app-api/adhoc/query?label=rgData";
    public static final String URL_CARD_DATA = URL_BASE + "/app-api/adhoc/query?label=cardData";



    static {
        try {
            trustAllHttpsCertificates();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustManagers = new TrustManager[1];
        trustManagers[0] = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustManagers, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }



    private static final String header_str = "Host: wukong2.tingyun.com\n" +
            "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"\n" +
            "dnt: 1\n" +
            "sec-ch-ua-mobile: ?0\n" +
            "authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkY2MwMzI2ZC1lOTU1LTQ1ZGUtOTY2Ny0wYjkxMGE3MTNmMjQiLCJpZCI6Ijc2MGRjNDQzLTUwYTktNGIwNC05NjQ4LTc0ZWU2MWUwOGZhMyIsIm5hbWUiOiI3MjEwZWZiNC05OWNmLTQ3YTktOTUyZi1kZWU1OWU5Njk1MzQiLCJjb2RlIjoiZGI5NDUxYjQtNGZiMi00MDhhLTg0NGItZTExNmQ2NmQ4YTQ2IiwiYWdyZWVtZW50SWQiOiJiMmZlMGQwNy0yZmExLTQ5MmUtYTU1Yy01NGU1MzRlNDNlNTgiLCJ0eXBlIjoiOGU2NTIxZWQtZmQ4Yy00YmI3LTg0M2MtOGYzYzUzNDc4N2Y0IiwicmlnaHRSYW5nZSI6IjI0Y2E2ODBkLWI5MzgtNGZhOS1hMTU5LTYwNjhiZjNmODdkYiIsInJhbmRvbSI6ImM3MzJmYzlkLTIwMTAtNDVhOS04ZDdhLTI5MjdlZDdiNDY1YSJ9.YbCNXM4Zi8jiHezkSr9WLfQtQTf7E4M6TZZXFucK7Ohm8nR3xdX-yRidwk8lamnSkgQbj3K-2aqgRAGrJzeyD2modyj_-CrGAQXza4z179MRYqAsLHT2-wGJiZfWlH_5KAuGjemLxzJpFB99y-JJtaNnlkSGdcXNK_H5TLW-yFU\n" +
            "content-type: application/json\n" +
            "accept: application/json, text/plain, */*\n" +
            "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36\n" +
            "sec-ch-ua-platform: \"macOS\"\n" +
            "origin: https://wukong2.tingyun.com\n" +
            "sec-fetch-site: same-origin\n" +
            "sec-fetch-mode: cors\n" +
            "sec-fetch-dest: empty\n" +
            "referer: https://wukong2.tingyun.com/app/network/request\n" +
            "accept-encoding: gzip, deflate, br\n" +
            "accept-language: zh-CN,zh;q=0.9\n" +
            "Cookie: Hm_lvt_0bf60c32bdcb7f1c056055ca1b82fcff=1626662789; Hm_lvt_48edf09a23737727fd8d5b7927a364c9=1626662789; wk_default_release_uid=8f8fa3a85e28a7d61b7c54643821d798a7e87286; wk_api_gateway_release_uid=c3e28c47fd155e3cec896e408d1d52fd34fd49ee; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=zh-CN; Hm_lvt_93daecc2db29efa5d83566cf580e84af=1639366156,1639708978,1639968340,1640087981; Hm_lpvt_93daecc2db29efa5d83566cf580e84af=1640140698; CASTGC=TGT-2076058-jwpYXsSSzEGIhDralnam3WIz9hgfvqZaFENd1mnU67FOOhh3E0-account.tingyun.com\n";
    private static final Map<String, String> header = new LinkedHashMap<>(16, 1.0F);

    static {
        for (String kv : header_str.split("\n")) {
            String[] ss = kv.split(":");
            header.put(ss[0].trim(), ss[1].trim());
        }
    }

    private static final Gson gson = new Gson();

    public static <T> T openHttps(String url, String body, Class<T> clazz) throws Exception {
        URL u = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) u.openConnection();
        for (Map.Entry<String, String> e : header.entrySet()) {
            conn.setRequestProperty(e.getKey(), e.getValue());
        }
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");

        conn.connect();

        Writer writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(body);
        writer.flush();

        InputStream gzip = new GZIPInputStream(conn.getInputStream());
        Reader reader = new InputStreamReader(gzip);

        T t;
        try {
            t = gson.fromJson(reader, clazz);
        }
        catch (Exception e) {
            e.printStackTrace();
            t = null;
        }

        writer.close();
        reader.close();
        conn.disconnect();

        return t;
    }
    public static SlowTable[] querySlowTable(SlowTableRequest slowTable) throws Exception {
        return openHttps(URL_SLOW_TABLE,
//                "{\"timePeriod\":1440,\"endTime\":\"2021-12-21 00:00:00\",\"datasource\":\"APP_NETWORK_DATA\",\"metrics\":[\"requestCount\",\"throughput\",\"responseTime\",\"networkTime\",\"dnsTime\",\"httpErrorRate\",\"connectTime\",\"sslTime\",\"firstPacketTime\",\"remainPacketTime\",\"localQueueTime\",\"networkErrorRate\",\"trafficConsumption\",\"networkSpeed\",\"bytesSend\",\"bytesReceived\",\"httpErrorCount\",\"networkErrorCount\",\"slowCount\",\"slowRequestRate\"],\"dimensions\":[\"hostId\",\"requestType\"],\"filters\":[{\"name\":\"mobileAppVersionId\",\"value\":[19326,19521,19395]},{\"name\":\"mobileAppId\",\"value\":[100059],\"operator\":\"IN\"}],\"orderByExprs\":\"requestCount DESC\",\"limit\":1000,\"render\":\"list\"}",
                gson.toJson(slowTable),
                SlowTable[].class);
    }

    public static RegionData queryRegionData(SlowTableRequest slowTable) throws Exception {
        return openHttps(URL_REGION_DATA,
                gson.toJson(slowTable),
                RegionData.class);
    }

    public static CardData[] queryCardData(SlowTableRequest slowTable) throws Exception {
        return openHttps(URL_CARD_DATA,
                gson.toJson(slowTable),
                CardData[].class);
    }
}
