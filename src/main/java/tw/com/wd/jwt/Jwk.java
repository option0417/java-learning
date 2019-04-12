package tw.com.wd.jwt;

public class Jwk {
    public static final byte[] getJwk() {
        String base64Key = "AyM1SysPpbyDfgZld3umj1qzKObwVMkoqQ-EstJQLr_T-1qS0gZH75aKtMN3Yj0iPS4hcgUuTwjAzZr1Z9CAow";
        return Base64Util.fromBase64(base64Key);
    }
}
