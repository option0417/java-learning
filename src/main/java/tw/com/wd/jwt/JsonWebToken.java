package tw.com.wd.jwt;

public class JsonWebToken {
    private static final String CONCAT_SYMBOL = ".";

    private String jwtHeader;
    private String jwtPayload;
    private String jwtSignature;


    public JsonWebToken(String jwtHeader, String jwtPayload, String jwtSignature) {
        super();
        this.jwtHeader = jwtHeader;
        this.jwtPayload = jwtPayload;
        this.jwtSignature = jwtSignature;
    }

    public JsonWebToken(String jwtString) {
        super();
        String[] jwtParts = jwtString.split(CONCAT_SYMBOL);
        this.jwtHeader = jwtParts[0];
        this.jwtPayload = jwtParts[1];
        this.jwtSignature = jwtParts[2];
    }

    public String getJwtHeader() {
        return jwtHeader;
    }

    public String getJwtPayload() {
        return jwtPayload;
    }

    public String getJwtSignature() {
        return jwtSignature;
    }

    public String toJwtString(String jwtHeader, String jwtPayload, String jwtSignature) {
        return jwtHeader + CONCAT_SYMBOL + jwtPayload + CONCAT_SYMBOL + jwtSignature;
    }
}
