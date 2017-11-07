package tw.com.wd.utils;


import org.apache.commons.codec.binary.Hex;
import org.apache.hadoop.hbase.util.Bytes;
import org.joda.time.DateTime;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class FetchSIPAccount {
    private static final String PREFIX_JSON = "{\"CS210";
    private static final String MD5_PREFIX  = "3IM:3IMgetaccount:";
    private static final String REQ_FORMAT  = "jsonString={\"action\":\"3IM_getaccount\" ,\"nonce\" :\"%s\",\"checkcode\":" + "\"%s\"" + "}";
    private static final int JSON_CHAR_SIZE = 30000;

    // Use custom TrustManager and HostnameVerifier to skip invalid cert
    private static final TrustManager[] TRUST_ALL_CERT = new TrustManager[] { new X509TrustManager() {
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType ) {}
        @Override
        public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {}
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

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.printf("No CS210 address.");
            return;
        }

        String[] cs210Addreses  = args[0].split(",");
        List<String> sipResults = new ArrayList<String>();

        // https://<ip>:5088/json_3IM_getCS210account.php
        String protocol = "https://";
        String port     = ":5088";
        String path      = "/json_3IM_getCS210account.php";

        for (String cs210Address : cs210Addreses) {
            FetchSIPAccount fetchSIPAccount = new FetchSIPAccount();

            String sipJSON = fetchSIPAccount.fetchSIPAccountJSON(protocol + cs210Address + port + path);
            if (fetchSIPAccount.isValidSIPJSON(sipJSON)) {
                sipResults.add(sipJSON);
            }
        }

        writeToFile(sipResults.toArray(new String[sipResults.size()]));
    }

    private String fetchSIPAccountJSON(String url) {
        OutputStream outputStream   = null;
        InputStream inputStream     = null;
        try {
            String requestData  = createRequestBody();

            HttpsURLConnection httpsURLConnection = createHttsURLConnection(url);

            outputStream = httpsURLConnection.getOutputStream();
            outputStream.write(Bytes.toBytes(requestData));
            outputStream.close();

            inputStream                     = httpsURLConnection.getInputStream();
            StringBuilder responseBuilder   = new StringBuilder(JSON_CHAR_SIZE);
            int charInt                     = -1;
            while ( (charInt = inputStream.read()) != -1) {
                char ch = (char) charInt;
                responseBuilder.append(ch);
            }
            inputStream.close();

            return responseBuilder.toString();
        } catch (Throwable t) {
            t.printStackTrace();
            return "";
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String createRequestBody() {
        String dateString   = getDateString();
        String salt         = getSalt();
        String md5String    = getMD5String(dateString, salt);
        return String.format(REQ_FORMAT, dateString + salt, md5String);
    }

    private String getDateString() {
        DateTime dateTime = new DateTime(System.currentTimeMillis());
        return new StringBuilder(8)
                .append(String.valueOf(dateTime.getYear()))
                .append(String.valueOf(dateTime.getMonthOfYear()))
                .append(String.valueOf(dateTime.getDayOfMonth()))
                .toString();
    }

    private String getSalt() {
        return String.valueOf(new SecureRandom(Bytes.toBytes(System.currentTimeMillis())).nextInt(31) % 100);
    }

    private String getMD5String(String dateString, String salt) {
        // 18: Length of MD5_PREFIX
        // 8: Length of dateString
        // 3: Length of salt
        String preMD5String =
                new StringBuilder(18 + 8 + 3)
                        .append(MD5_PREFIX)
                        .append(dateString)
                        .append(salt)
                        .toString();
        System.out.println("Pre-MD5String: " + preMD5String);

        byte[] preMD5Bytes;
        try {
            preMD5Bytes = preMD5String.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            preMD5Bytes = preMD5String.getBytes();
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(preMD5Bytes);

            byte[] digest       = messageDigest.digest();
            String md5String    = new String(Hex.encodeHex(digest));
            System.out.println("MD5: " + md5String);
            return md5String;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpsURLConnection createHttsURLConnection(String url) {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, TRUST_ALL_CERT, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            URL sipAccountURL = new URL(url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) sipAccountURL.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
            httpsURLConnection.setHostnameVerifier(ALL_HOST_VALID);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);

            return httpsURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidSIPJSON(String responseJSON) {
        if (responseJSON.indexOf(PREFIX_JSON) != 0) {
            return false;
        } else {
            return true;
        }
    }

    private static void writeToFile(String... sipJSONs) {
        FileOutputStream fos = null;

        for (int cnt = 0; cnt < sipJSONs.length; cnt++) {
            String sipJSON = sipJSONs[cnt];

            try {
                fos = new FileOutputStream(new File("./sip" + cnt + ".json"));
                fos.write(sipJSON.getBytes(Charset.forName("UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
