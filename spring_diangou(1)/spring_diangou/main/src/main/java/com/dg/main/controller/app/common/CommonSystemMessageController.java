package com.dg.main.controller.app.common;

import com.dg.main.Entity.message.SystemMessage;
import com.dg.main.repository.message.SystemMessageRepository;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common/v1/systemMessage")
@Api(value = "通用-系统消息")
public class CommonSystemMessageController {

    @Autowired
    private SystemMessageRepository systemMessageRepository;

    @RequestMapping("/list")
    @ApiOperation(value = "list",notes = "消息记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示记录数")
    })
    public Result list(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<SystemMessage> page = systemMessageRepository.findAll(pageRequest);
        List<SystemMessage> content = page.getContent();
        System.out.println(content);
        return Result.successResult(content);
    }
}
