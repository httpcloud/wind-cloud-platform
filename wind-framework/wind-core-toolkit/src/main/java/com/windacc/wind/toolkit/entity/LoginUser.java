package com.windacc.wind.toolkit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 18:11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginUser extends User {

    private static final long serialVersionUID = -4216862192732401875L;

    private Set<String> roleCodes;

}
