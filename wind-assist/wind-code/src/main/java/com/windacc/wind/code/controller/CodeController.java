package com.windacc.wind.code.controller;

import com.windacc.wind.code.service.ICodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description desc   </p>
 *
 * @author windacc
 * @date 2021/5/15 14:38
 */
@RestController
@Api(tags = "代码生成器")
public class CodeController {

    private final ICodeService codeService;

    public CodeController(ICodeService codeService) {
        this.codeService = codeService;
    }

    @ApiOperation(value = "代码生成器", notes = "自动生成输入表的实体类及mapper类")
    @ApiImplicitParams({ @ApiImplicitParam(name = "tables", value = "生成代码的表，多个表以逗号隔开", paramType = "query") })
    @GetMapping(value = "/code/freemarker", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void freemarker(@RequestParam String tables) {
        codeService.codeGen(tables);
    }

}
