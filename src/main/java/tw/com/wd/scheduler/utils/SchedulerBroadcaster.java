package tw.com.wd.scheduler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;

public class SchedulerBroadcaster {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerBroadcaster.class);
    private static final String URL_FORMAT = "https://%s/apiv1/sys/command";
    private static final String HEADER_USER_ID = "ui";
    private static final String HEADER_ACCESS_TOKEN = "at";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_ACCEPT_TYPE = "Accept";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final class InstanceHolder {
        private static final SchedulerBroadcaster INSTANCE = new SchedulerBroadcaster();
    }

    public static SchedulerBroadcaster getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private SchedulerBroadcaster() {
        super();
    }

    // Use custom TrustManager and HostnameVerifier to skip invalid cert
    private static final TrustManager[] TRUST_ALL_CERT = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType) {}
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType) {}
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    } };

    private static final HostnameVerifier ALL_HOST_VALID = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public String broadcast(URL remoteURL, String schedulerCommand) {
        byte[] contentBytes = schedulerCommand.getBytes(Charset.forName("UTF8"));
        return broadcast(remoteURL, contentBytes);
    }

    public String broadcast(URL remoteURL, byte[] contentBytes) {
        HttpURLConnection connection    = null;
        OutputStream outputStream       = null;
        InputStream inputStream         = null;
        try {
            connection = buildHttpURLConnection(remoteURL);

            outputStream = connection.getOutputStream();
            outputStream.write(contentBytes);

            if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                byte[] _1KBytes     = new byte[1024];
                byte[] resultBytes  = new byte[0];
                int readCount       = -1;
                inputStream         = connection.getInputStream();

                while ((readCount = inputStream.read(_1KBytes)) != -1) {
                    byte[] newByteArray = new byte[resultBytes.length + readCount];
                    System.arraycopy(resultBytes, 0, newByteArray, 0, resultBytes.length);
                    System.arraycopy(_1KBytes, 0, newByteArray, resultBytes.length, readCount);
                    resultBytes = newByteArray;
                }

                return new String(resultBytes, Charset.forName("UTF8"));
            } else {
                return connection.getResponseMessage();
            }
        } catch (Throwable t) {
            LOG.error("Caught Exception: {}", t);
            return "";
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Throwable t) {
                LOG.error("Caught Exception in finally: {}", t);
            }
        }
    }

    private HttpURLConnection buildHttpURLConnection(URL remoteURL) {
        HttpsURLConnection httpsURLConnection = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, TRUST_ALL_CERT, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            httpsURLConnection  = (HttpsURLConnection) remoteURL.openConnection();

            httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
            httpsURLConnection.setHostnameVerifier(ALL_HOST_VALID);

            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.addRequestProperty(HEADER_ACCEPT_TYPE, CONTENT_TYPE_JSON);
            httpsURLConnection.addRequestProperty(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            return httpsURLConnection;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
