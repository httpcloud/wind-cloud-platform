package com.windacc.wind.user.controller;

import com.windacc.wind.toolkit.entity.LoginUser;
import com.windacc.wind.toolkit.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 10:30
 */
@RestController
@RequestMapping("department")
public class GroupController {

    @GetMapping("getMember")
    public LoginUser getMemberByOragnize(@RequestParam String organize) {
        String[] organ = organize.split(":");
        if (organ.length != 2) {
            throw new BusinessException("参数错误，参数必须是（用户群组类型：群组id");
        }




        return null;
    }

}
