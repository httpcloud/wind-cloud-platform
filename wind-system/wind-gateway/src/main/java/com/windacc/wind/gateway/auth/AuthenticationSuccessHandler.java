package com.windacc.wind.gateway.auth;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.windacc.wind.security.entity.LoginUserDetails;
import com.windacc.wind.toolkit.constants.HeadersConstant;
import com.windacc.wind.toolkit.entity.LoginClient;
import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.entity.User;
import com.windacc.wind.toolkit.utils.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/16
 */
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        MultiValueMap<String, String> headerValues = new LinkedMultiValueMap<>(4);
        Object principal = authentication.getPrincipal();
        //客户端模式只返回一个clientId
        if (principal instanceof User) {
            User user = (User)authentication.getPrincipal();
        }
        OAuth2Authentication oauth2Authentication = (OAuth2Authentication) authentication;
        String clientId = oauth2Authentication.getOAuth2Request().getClientId();

        Authentication userAuthentication = oauth2Authentication.getUserAuthentication();
        LoginUser loginUser = new LoginUser();
        if (userAuthentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) userAuthentication;
            if (authenticationToken.getPrincipal() instanceof LoginUserDetails) {
                LoginUserDetails loginUserDetails= (LoginUserDetails) authenticationToken.getPrincipal();
                BeanUtils.copyProperties(loginUserDetails,loginUser);
                headerValues.add(HeadersConstant.USERNAME_HEADER, loginUser.getUsername());
                headerValues.add(HeadersConstant.USER_JSON_HEADER, URLUtil.encode(JsonUtil.toJson(loginUser)));

            }
        }
        if (StrUtil.isNotBlank(clientId)) {
            headerValues.add(HeadersConstant.CLIENT_ID_HEADER, clientId);
            LoginClient loginClient = convertEntity(oauth2Authentication);
            headerValues.add(HeadersConstant.CLIENT_JSON_HEADER, URLUtil.encode(JsonUtil.toJson(loginClient)));
        }

        ServerWebExchange exchange = webFilterExchange.getExchange();
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
            .headers(h -> {
                h.addAll(headerValues);
            })
            .build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return webFilterExchange.getChain().filter(build);
    }

    private LoginClient convertEntity(OAuth2Authentication authentication) {
        OAuth2Request request = authentication.getOAuth2Request();
        LoginClient loginClient = new LoginClient();
        loginClient.setClientId(request.getClientId());
        loginClient.setAuthenticated(authentication.getUserAuthentication().isAuthenticated());

        Set<String> authorities = new HashSet<>();
        request.getAuthorities().forEach(grantedAuthority -> {
            authorities.add(grantedAuthority.getAuthority());
        });
        loginClient.setAuthorities(authorities);
        loginClient.setScope(request.getScope());
        loginClient.setGrantType(request.getGrantType());

        return loginClient;
    }

}
