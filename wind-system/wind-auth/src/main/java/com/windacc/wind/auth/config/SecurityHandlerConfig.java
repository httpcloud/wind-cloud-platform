package com.windacc.wind.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windacc.wind.auth.handle.OauthLogoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityHandlerConfig {

	@Resource
	private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

	// url匹配器
//	private AntPathMatcher pathMatcher = new AntPathMatcher();

	/**
	 * 登陆成功，返回Token 装配此bean不支持授权码模式
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {

		return new SavedRequestAwareAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}
	
	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {

			public static final String BAD_MSG = "Bad credentials";

			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				OAuth2Exception oAuth2Exception;
				if (e instanceof InvalidGrantException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				}else if (e instanceof InternalAuthenticationServiceException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof RedirectMismatchException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof InvalidScopeException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				}  else if (e instanceof BadCredentialsException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				}  else if (e instanceof InvalidClientException) {
					oAuth2Exception = new InvalidClientException(e.getMessage());
				} else {
					oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
				}

				ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
				ResponseEntity.status(oAuth2Exception.getHttpErrorCode());

				OAuth2Exception body = response.getBody();
				if (body != null) {
					body.addAdditionalInformation("code", oAuth2Exception.getHttpErrorCode() + "");
					body.addAdditionalInformation("message", oAuth2Exception.getMessage());
				}

				//response.getBody().addAdditionalInformation("code", oAuth2Exception.getHttpErrorCode() + "");
				//response.getBody().addAdditionalInformation("msg", oAuth2Exception.getMessage());

				return response;
			}

		};
	}
	
	//@Bean
    //public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
    //    OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
    //    expressionHandler.setApplicationContext(applicationContext);
    //    return expressionHandler;
    //}
	
	@Bean
	public OauthLogoutHandler oauthLogoutHandler() {
		return new OauthLogoutHandler();
	}
	 

}
