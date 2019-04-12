package tw.com.wd.jwt;

import com.google.gson.annotations.SerializedName;


public class JwtPayload {
    @SerializedName("iss")
    private String issuer;

    @SerializedName("exp")
    private long expireTimestamp;

    @SerializedName("\"http://example.com/is_root\":true")
    private boolean isRoot;


    public JwtPayload(String issuer, long expireTimestamp) {
        super();
        this.issuer = issuer;
        this.expireTimestamp = expireTimestamp;
    }

    public String getIssuer() {
        return issuer;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
