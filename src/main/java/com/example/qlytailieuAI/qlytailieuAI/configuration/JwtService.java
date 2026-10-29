package com.example.qlytailieuAI.qlytailieuAI.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Service
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtService {
    @Value("${jwt.signerkey}")
    String signerKey;

    @Value("${jwt.access-token-validity-in-seconds:900}")
    long accessTokenValidityInSeconds;

    @Value("${jwt.refresh-token-validity-in-seconds:604800}")
    long refreshTokenValidityInSeconds;

    public String generateToken(String subject, String authority, String tokenType, long validityInSeconds) {
        try {
            Date now = new Date();
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(subject)
                    .issuer("smart-document-management")
                    .issueTime(now)
                    .expirationTime(new Date(now.getTime() + validityInSeconds * 1000))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope", authority)
                    .claim("token_type", tokenType)
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
            signedJWT.sign(new MACSigner(signerKey.getBytes(StandardCharsets.UTF_8)));
            return signedJWT.serialize();
        } catch (JOSEException exception) {
            throw new IllegalStateException("Không thể tạo JWT. Hãy kiểm tra jwt.signerkey.", exception);
        }
    }

    public SignedJWT parseAndVerify(String token) throws java.text.ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(new MACVerifier(signerKey.getBytes(StandardCharsets.UTF_8)));
        if (!verified) {
            throw new JOSEException("Invalid JWT signature");
        }
        return signedJWT;
    }
}
