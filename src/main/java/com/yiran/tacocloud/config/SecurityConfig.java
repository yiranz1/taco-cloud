package com.yiran.tacocloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchBase("(uid={0})")
                .groupSearchBase("ou=group")
                .groupSearchFilter("member={0}")
                .passwordCompare()
                // 指定与给定密码进行比对的是passcode属性，指定密码转码器为bcrypt加密
                .passwordAttribute("passcode")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
