package com.grevu.app.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jason on 14. 11. 6..
 */
public class HttpNetworkUtil {
    private static final int HTTP_TIMEOUT = 5000;

    /**
     * HTTP Post
     * */
    public static HttpResult sendHttpPost(String url) {


        HttpResult result = new HttpResult();

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, HTTP_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, HTTP_TIMEOUT);
        HttpPost request = new HttpPost(url);

        HttpClient client = new DefaultHttpClient();

        InputStream inputStream = null;

        try {
            HttpResponse httpResponse = client.execute(request);
            HttpEntity httpEntity = httpResponse.getEntity();

            result.setStatus(httpResponse.getStatusLine().getStatusCode());

            if (httpEntity != null) {
                inputStream = httpEntity.getContent();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            String message = stringBuilder.toString().trim();

            Logger.d("[HttpNetworkUtil] message : " + message);

            result.setResponseMsg(message);

        } catch (ClientProtocolException cpe) {
            result.setStatus(HTTP_STATUS_RESPONSE.PROTOCOL_ERROR);
            cpe.printStackTrace();
        } catch (ConnectTimeoutException cte) {
            result.setStatus(HTTP_STATUS_RESPONSE.CONNECTION_TIMEOUT_ERROR);
        } catch (Exception e) {
            result.setStatus(HTTP_STATUS_RESPONSE.SERVER_ERROR);
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        return result;
    }

    /** Http status code */
    public static final class HTTP_STATUS_RESPONSE {

        public static final int SUCCESS = 0;

        /** neep305:http 200ok */
        public static final int HTTP_SUCCESS = 200;

        /** neep305 : 서버 연결 실패 */
        public static final int SERVER_ERROR = 7001;

        /** neep305: 3G, WiFi 네트워크 문제로 인한 연결실패 */
        public static final int CONNECTION_TIMEOUT_ERROR = 9001;

        /** neep305:HTTP PROTOCOL Error */
        public static final int PROTOCOL_ERROR = 9002;
    }
}
