import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiyajian
 * create: 2018-11-04
 */
public class JwtTest {

    /**
     * 服务器端秘钥
     */
    private static final String SERVER_SERCRT = "helloworld";


    public static String createToken(String userName){
        // 签发时间
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE, 10);
        // 过期时间
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create().withHeader(map)
                .withClaim("iss", "Service")
                .withClaim("aud", "APP")
                .withClaim("user_id",userName)
                .withIssuedAt(iatDate)
                .sign(Algorithm.HMAC256(SERVER_SERCRT));

    }

    public static Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SERVER_SERCRT)).build();
        DecodedJWT jwt = null;
        try{
            jwt = verifier.verify(token);
        }
        catch (JWTVerificationException e){
            throw new RuntimeException();
        }
        return jwt.getClaims();
    }

    public static void main(String[] args) {

        String token = JwtTest.createToken("hellworld");
        System.out.println(token);

        Map<String, Claim> claimMap = JwtTest.verifyToken(token);
        for (Map.Entry<String, Claim> stringClaimEntry : claimMap.entrySet()) {
            System.out.println(String.format("%s : %s",
                    stringClaimEntry.getKey(),
                    stringClaimEntry.getValue().asString()));
        }
    }
}
