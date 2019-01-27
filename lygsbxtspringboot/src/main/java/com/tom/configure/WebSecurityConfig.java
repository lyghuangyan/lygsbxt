package com.tom.configure;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

import com.tom.model.SecurityUser;
import com.tom.service.UserInfoService;
//import com.jinge.handler.AuthenticationAccessDeniedHandler;
//import com.jinge.service.UserInfoService;
import com.tom.util.ApiResponse;

/**
 * 权限配置
 * @author tom
 * @since 2018-10-21 20:59:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // 控制权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * security5推荐使用更安全的BCryptPasswordEncoder
	 * 但为了兼容目前大部分以md5加密方案的项目使用md5加密
	 * @return
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userInfoService)
			.passwordEncoder(new PasswordEncoder() {

				@Override
				public String encode(CharSequence rawPassword) {
					return DigestUtils.md5DigestAsHex(
							rawPassword.toString().getBytes());
				}

				@Override
				public boolean matches(CharSequence rawPassword, 
						String encodedPassword) {
					return encodedPassword.equals(
							DigestUtils.md5DigestAsHex(
									rawPassword.toString().getBytes()));
				}
				
			});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//配置匿名用户权限
		http.anonymous().authorities("ANONYMOUS");
		http.authorizeRequests().and().formLogin().loginPage("/login_p")
			.loginProcessingUrl("/login").usernameParameter("username")
			.passwordParameter("password")
			.successHandler(new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(
						HttpServletRequest arg0, 
						HttpServletResponse arg1, 
						Authentication arg2)
						throws IOException, ServletException {
					arg1.setContentType("application/json;charset=utf-8");
					PrintWriter out = arg1.getWriter();
					SecurityUser securityUser = null;
					Object principal = 
							SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if(principal != null){
						if(principal instanceof SecurityUser) {
							securityUser = (SecurityUser) principal;
						}
					}
					ApiResponse apiResponse = new ApiResponse();
					apiResponse.setData(securityUser);
					apiResponse.setMsg("登录成功");
					//out.print(apiResponse);
					out.write(apiResponse.toJson());
					out.flush();
					out.close();
				}
			}).failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(
						HttpServletRequest arg0, 
						HttpServletResponse arg1, 
						AuthenticationException arg2)
						throws IOException, ServletException {
					arg1.setContentType("application/json;charset=utf-8");
					PrintWriter out = arg1.getWriter();
					ApiResponse apiResponse = new ApiResponse();
					apiResponse.setData(false);
					apiResponse.setMsg(arg2.getMessage());
					apiResponse.setCode("403");
					//out.print(apiResponse);
					out.write(apiResponse.toJson());
					out.flush();
					out.close();
				}
			}).permitAll().and().logout().permitAll()/*.and()
			.exceptionHandling().accessDeniedHandler(
					new AuthenticationAccessDeniedHandler())*/
			//关闭跨域
			.and().cors().and().csrf().disable();
	}
	
}
