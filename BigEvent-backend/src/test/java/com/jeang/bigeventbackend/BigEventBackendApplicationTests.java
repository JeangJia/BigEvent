package com.jeang.bigeventbackend;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BigEventBackendApplicationTests {

    @Test
    void testGen() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "jeang");
        claims.put("password", "jeang");
        // 生成jwt代码
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("jeang"));
        System.out.println(token);
    }

    @Test
    void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7InBhc3N3b3JkIjoiamVhbmciLCJ1c2VybmFtZSI6ImplYW5nIn0sImV4cCI6MTc5NTYyMzU5MH0" +
                ".spvcgoof3LRZaMNAMJBjtAt_EVfy3zPYACUFKnEIGRI";
        Map<String, Claim> claims = JWT
                .require(Algorithm.HMAC256("jeang"))
                .build()
                .verify(token)
                .getClaims();
        System.out.println(claims.get("user"));
    }

}
