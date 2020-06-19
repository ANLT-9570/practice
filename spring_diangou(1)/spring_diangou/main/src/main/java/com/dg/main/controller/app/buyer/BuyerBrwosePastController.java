package com.dg.main.controller.app.buyer;

import com.dg.main.Entity.Record;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RecordRepostory;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.RecordItemVo;
import com.dg.main.vo.RecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "用户-浏览记录")
@RequestMapping("/buyer/browse/past")
public class BuyerBrwosePastController {

    @Autowired
    private RecordRepostory recordRepostory;
    @Autowired
    private SpecificationsRepository specificationsRepository;
    @Autowired
    private MobileUserRepository mobileUserRepository;

    @PostMapping("/add")
    @ApiOperation(value = "add",notes = "添加浏览记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "record",value = "记录的信息")
    })
    public Result add(@RequestParam("userId")Long userId,@RequestParam("shopId")Long shopId,@RequestParam("specificationsId")Long specificationsId){
        Date date = new Date();
        //2019-12-24 03:05:40
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        System.out.println(">>>>>>>>>>>>>>>"+format1);
        Record record=new Record();
        record.setShopId(shopId);
        record.setUserId(userId);
        record.setSpecificationsId(specificationsId);
        //查询今天是否查看过该商品
        Record r1 = recordRepostory.findByUserIdAndSpecificationsIdAndBrowseTimeLikess(record.getUserId(),record.getSpecificationsId(),"%"+format1+"%");
        System.out.println(r1);

        if(r1 != null){
            recordRepostory.deleteById(r1.getId());
        }

        record.setBrowseTime(new Timestamp(new Date().getTime()));
        Record save = recordRepostory.save(record);
        if (save == null){
            return Result.failureResult(ExceptionCode.Failure.ADD_FAILURE);
        }
        return Result.successResult();
    }

    @GetMapping("/list")
    @ApiOperation(value = "list",notes = "所有的浏览记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result list(Long userId, @RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<RecordVo> records = new ArrayList<>();

        List<Record> groubs = recordRepostory.findByUserIdGroupBy(userId,offset,limit);
        for(Record record :groubs){
            Timestamp browseTime = record.getBrowseTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String NewbrowseTime = format.format(browseTime);
            RecordVo recordVo=new RecordVo();
            BeanUtils.copyProperties(record,recordVo);
            recordVo.setBrowseTime(record.getBrowseTime());
            List<RecordItemVo> recordItemVos= Lists.newArrayList();

            List<Record> res = recordRepostory.findByUserIdAndBrowseTime(userId,"%"+NewbrowseTime+"%");

            if(res.size() > 0){
                for(Record record1 : res){
                    RecordItemVo recordItemVo=new RecordItemVo();
//                    BeanUtils.copyProperties(record1,recordItemVo);
                    Long specificationsId = record1.getSpecificationsId();
                    Optional<Specifications> specifications = specificationsRepository.findById(specificationsId);
                    recordItemVo.setImg(specifications.get().getImg());
                    recordItemVo.setPrice(specifications.get().getPrice());
                    recordItemVo.setSpecificationsName(specifications.get().getName());
                    recordItemVos.add(recordItemVo);
                }
                recordVo.setItems(recordItemVos);
              //  records.add(res);
            }
            records.add(recordVo);
        }

        return Result.successResult(records);
    }

}
