package com.windacc.wind.toolkit.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/19
 */
@Data
public class LoginClient implements Serializable {

    private static final long serialVersionUID = -8606943296682665444L;

    private String clientId;

    private String grantType;

    private Set<String> scope;

    private String redirectUri;

    private Set<String> authorities;

    private boolean authenticated;

}
