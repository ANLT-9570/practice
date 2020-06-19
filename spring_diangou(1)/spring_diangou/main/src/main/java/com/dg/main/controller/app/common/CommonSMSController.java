package com.dg.main.controller.app.common;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.util.AliyunSmsUtils;
import com.dg.main.util.Result;
import com.dg.main.util.SMSCodeGenerator;
import com.dg.main.util.SMSContainerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dg.main.exception.ExceptionCode.Failure.*;


@RestController
@RequestMapping("/common/sms")
@Api(tags = "通用-阿里云短信")
public class CommonSMSController {

    @PostMapping("/send")
    @ApiOperation(value = "send",notes = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "type",value = "类型")
    })
    public Result send(@RequestParam("phone") String phone, @RequestParam(value = "type",defaultValue = "1") Integer type){
        if(phone==null){
            return Result.failureResult(NO_PHONE_NUMBER);
        }
        String code= SMSCodeGenerator.code4();
        SMSContainerUtils.container.put(phone,code);
        try {
            SendSmsResponse sendSmsResponse  = AliyunSmsUtils.sendSmsv1(phone, code);
            if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
                System.out.println("短信发送成功！");
                return Result.successResult();

            }else {
                System.out.println("短信发送失败！");
                return Result.failureResult(sendSmsResponse.getCode(),sendSmsResponse.getMessage());
            }
        } catch (ClientException e1) {

            e1.printStackTrace();

        }
      //  return Result.successResult();
        return Result.failureResult(SYSTEM_ERROR);
    }
    @PostMapping("/verify")
    @ApiOperation(value = "verify",notes = "短信验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "code",value = "短信编号"),
            @ApiImplicitParam(name = "type",value = "类型")
    })
    public Result verify(String phone,String code,Integer type){
        if(phone==null&&code==null){
            return Result.failureResult(NO_PHONE_NUMBER_AND_CODE);
        }
        String tempCode=SMSContainerUtils.container.get(phone);
        if(!StringUtils.isEmpty(tempCode)){
            if(tempCode.equals(code)){
                SMSContainerUtils.container.remove(phone);
                return Result.successResult();
            }
        }
        return Result.successResult(ERROR_NUMBER);
    }
}
