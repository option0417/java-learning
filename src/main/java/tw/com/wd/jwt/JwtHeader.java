package tw.com.wd.jwt;

import com.google.gson.annotations.SerializedName;


public class JwtHeader {
    @SerializedName("typ")
    private String type;
    @SerializedName("alg")
    private String algorithm;


    public JwtHeader(String type, String alg) {
        super();
        this.type = type;
        this.algorithm = alg;
    }

    public String getType() {
        return type;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
