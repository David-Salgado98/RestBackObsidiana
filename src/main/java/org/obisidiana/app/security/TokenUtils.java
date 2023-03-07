package org.obisidiana.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "h7OxiFKBCMD-wzHUF9H1kNqVotzUggdm=zolpmeDS00LNaECXq!Z7gBKquZYMzKXuwfR3rI?FApJqxsisz3=2Ej?iw-koeXg4AfetD6DGLamML58vZ?2!HXKSYrtbCUOD8ujq4eqUiTDXKJnn/9SB97d8g8mfk4DodBCt3Bvu6/d656GFXgdxX2UWq66sjW?DSQ/4n-ZPW8C95?TIpqN4E=jJyR510DxBnCWxCUsWociusqHWvm!LMIccEbkjgxj";
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 300L; //5*60

    public static String createToken(String fullname,String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS*1000;//en milis
        Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);
        Map<String,Object> payload = new HashMap<>();
        payload.put("name",fullname);




        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(payload)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
       try{ Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email= claims.getSubject();
        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList() );
       }
       catch (Exception e) {
           System.out.println(e);
           System.out.println(token);
           return null;
       }

    }

}
