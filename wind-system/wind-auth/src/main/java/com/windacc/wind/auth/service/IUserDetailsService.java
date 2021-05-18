package com.windacc.wind.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 16:16
 */
public interface IUserDetailsService extends UserDetailsService, SocialUserDetailsService {
}
