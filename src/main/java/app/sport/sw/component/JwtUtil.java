package app.sport.sw.component;

import app.sport.sw.domain.user.User;
import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.exception.TokenError;
import app.sport.sw.exception.TokenException;
import app.sport.sw.mvc.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;

    private static final long ACCESS_TOKEN_EXPIRES_TIME = 60 * 60 * 24 * 1;
    private static final long REFRESH_TOKEN_EXPIRES_TIME = 60 * 60 * 24 * 7;
    private final SecretKey secretKey;

    @Autowired
    public JwtUtil(UserRepository userRepository, @Value("${jwt.secret-key}") String secretKey) {
        this.userRepository = userRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateAccessToken(User user) {
        return createToken(ACCESS_TOKEN_EXPIRES_TIME, user.getUserSocial());
    }
    
    public String generateRefreshToken(User user) {
        return createToken(REFRESH_TOKEN_EXPIRES_TIME, user.getUserSocial());
    }


    private String createToken(long expirationPeriod, UserSocial userSocial) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userSocial.getSocialId());
        claims.put("iss", userSocial.getProvider().name());
        long now = System.currentTimeMillis();
        return Jwts.builder()
            .signWith(secretKey, Jwts.SIG.HS256)
            .expiration(new Date(now + (expirationPeriod * 1000)))
            .issuedAt(new Date(now))
            .claims(claims)
            .compact();
    }
    
    public void validateAccessToken(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TokenException(TokenError.ACCESS_TOKEN_REQUIRE);
        }

        if (isExpiredToken(accessToken)) {
            throw new TokenException(TokenError.TOKEN_EXPIRED);
        }

    }

//    public void validateRefreshToken(String refreshToken) {
//        String refreshTokenInDB = userRepository.findByRefreshToken(refreshToken)
//            .map((user) -> user.getUserSocial().getRefreshToken())
//            .orElseThrow(() -> new TokenException(TokenError.TOKEN_EXPIRED));
//
//        if (isExpiredToken(refreshTokenInDB)) {
//            throw new TokenException(TokenError.TOKEN_EXPIRED);
//        }
//
//    }

    private boolean isExpiredToken(String token) {
        Date expiraionDate = extractClaim(token, Claims::getExpiration);
        return expiraionDate.before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claimsResolver.apply(claims);

    }


    public Map<String, String> initToken(User saveOrFindUser) {
        Map<String, String> token = new HashMap<>(2);
        String accessToken = generateAccessToken(saveOrFindUser);
        String refreshToken = generateRefreshToken(saveOrFindUser);

        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);

        saveOrFindUser.setAccessToken(accessToken);
//        saveOrFindUser.setRefreshToken(refreshToken);

        return token;

    }


    public ResponseToken refreshingAccessToken(User user, String refreshToken) {
        String accessToken = generateAccessToken(user);

        user.setAccessToken(accessToken);
//        user.setRefreshToken(refreshToken);

        return new ResponseToken(accessToken);
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        } else {
            throw new TokenException(TokenError.ACCESS_TOKEN_REQUIRE);
        }
    }

}
