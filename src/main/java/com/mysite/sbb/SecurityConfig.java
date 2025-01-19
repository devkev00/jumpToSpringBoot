package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 활성화
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                        // 미인증된 모든 페이지 요청 허락
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                        // /h2-console/로 시작하는 모든 url은 csrf 검증을 하지 않음
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                        // x-frame-options 헤더 설정 변경 -> h2 콘솔 오류 해결
                        // h2 콘솔은 프레임 구조로 작성되었기 때문에 x-frame-option 헤더 기본값인 deny에 막혀서 페이지가 안 보이는 오류가 발생한다.
                        // sameorigin: 프레임에 포함된 웹 페이지가 동일한 사이트에서 제공할 때에만 사용이 허락된다.
        ;
        return http.build();
    }
}
