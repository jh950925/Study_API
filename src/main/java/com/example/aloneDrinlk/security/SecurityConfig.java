//package com.example.aloneDrinlk.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@Slf4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api/github/userinfo").permitAll()    // LoadBalancer Chk
////                .antMatchers("/manage").hasAuthority("ADMIN") //ADMIN 설정
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/view/login") // 커스텀 페이지로 로그인 페이지 변경
////                .loginProcessingUrl("/loginProc") // form 태그에서 action = "loginProc" 지정하면 자동으로 id pass 보내준다.
////                .usernameParameter("id") // 기본 input type
////                .passwordParameter("pw") // 기본 input type
//                .defaultSuccessUrl("/main", true)
//                .permitAll()
////                .and()
////                .logout() // 로그아웃 uri 설정
//        ;
//    }
//
//    /**
//     * 시큐리티 예외처리
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**");
//    }
//}
