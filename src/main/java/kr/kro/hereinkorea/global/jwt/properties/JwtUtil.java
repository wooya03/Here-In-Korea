package kr.kro.hereinkorea.global.jwt.properties;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kr.kro.hereinkorea.domain.member.exception.NotFoundMemberException;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.global.jwt.enums.JwtType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;

    public String generateAccessToken(String memId){
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(memId)
                .setIssuedAt(
                        new Date(System.currentTimeMillis())
                )
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtProperties.getExpiration())
                )
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public String generateRefreshToken(String memId){
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(memId)
                .setIssuedAt(
                        new Date(System.currentTimeMillis())
                )// 발행
                .setExpiration(
                        new Date(System.currentTimeMillis()+jwtProperties.getRefreshExpiration())
                )//만료
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecretKey())
                .compact();
    }

    //토큰 복호화 및 클레임 확인
    public Jws<Claims> getClaims(String token)
    {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    ;
        } catch (ExpiredJwtException e){
            throw new JwtException("Expired JWT");
        } catch (UnsupportedJwtException e){
            throw new JwtException("Unsupporeted JWT");
        }catch (MalformedJwtException e){
            throw new JwtException("Invalid JWT"); //서명예외
        }catch (SignatureException e){
            throw new JwtException("Unsupporeted JWT");
        } catch (IllegalArgumentException e){
            throw new JwtException("JWT claims string is empty");
        }
    }

    //사용자 인증
//    public Authentication getAuthentication(String token) {
//        Jws<Claims> claims = getClaims(token);
//
//        if (isWrongType(claims, JwtType.ACCESS)) {
//            throw new JwtException("JWT Type Exception!");
//        }
//        String memId = claims.getBody().getSubject();
//
//        MemberDTO memberDTO = memberRepository.
//                findByMemId(memId).
//                map(memberEntity -> MemberMapper.createDTO(memberEntity)
//                ).orElseThrow(() -> NotFoundMemberException.EXCEPTION
//                );
//         //유저디테일 작성예정
//        CustomUserDetails customUserDetails = CustomUserDetails.create(memberDTO);
//
//        return new UsernamePasswordAuthenticationToken
//                (customUserDetails, null, customUserDetails.getAuthorities());
//    }

    public boolean isWrongType(Jws<Claims> claims, JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }

}
