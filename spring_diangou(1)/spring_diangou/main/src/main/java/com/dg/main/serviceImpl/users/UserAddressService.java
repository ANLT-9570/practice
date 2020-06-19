package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    CustomerAddressRepository repository;
    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Transactional
    public Result save(CustomerAddress item){
        System.out.println(item);
        if(item.getIsDefault()==1) {
            CustomerAddress customerAddress = repository.findByUserIdAndIsDefault(item.getUserId(), 1);
            if(customerAddress!=null){
                customerAddress.setIsDefault(0);
//                String[] arr=customerAddress.getCity().split("-");
//                customerAddress.setCity(arr[1]);
//                customerAddress.setProvince(arr[0]);
                repository.save(customerAddress);
              //  repository.save(item);
                String[] arr=item.getCity().split("-");
                item.setCity(arr[1]);
                item.setProvince(arr[0]);
                repository.save(item);
                return Result.successResult();
            }
            String[] arr=item.getCity().split("-");
            item.setCity(arr[1]);
            item.setProvince(arr[0]);
            repository.save(item);
            return Result.successResult();
        }else{
            CustomerAddress customerAddress = repository.findByUserIdAndIsDefault(item.getUserId(), 1);
            if(customerAddress==null){
                customerAddress=new CustomerAddress();
                BeanUtils.copyProperties(item,customerAddress);
                String[] arr=customerAddress.getCity().split("-");
                customerAddress.setCity(arr[1]);
                customerAddress.setProvince(arr[0]);
                repository.save(customerAddress);


            }else{
                String[] arr=item.getCity().split("-");
                item.setCity(arr[1]);
                item.setProvince(arr[0]);
                repository.save(item);
                return Result.successResult();
            }
        }

        return Result.successResult();
    }
    public CustomerAddress getDefaultAddress(Long userId){
        return repository.findByUserIdAndIsDefault(userId,1);
    }
    public Result selectByID(Long userid, Integer offset,  Integer limit) {
        if(userid == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userid);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        List<CustomerAddress> list = repository.findByUserId(userid, pageRequest);
//        select * from customer_address where user_id = 18 order by is_default desc limit 0,10
        List<CustomerAddress> list = repository.findByUserIdPage(userid,offset,limit);
        return Result.successResult(list);

    }
    @Transactional
    public Result update(CustomerAddress item){
        System.out.println(item);
        if(item.getIsDefault()==1) {
            CustomerAddress customerAddress = repository.findByUserIdAndIsDefault(item.getUserId(), 1);
            if (customerAddress != null) {
                customerAddress.setIsDefault(0);
                repository.save(customerAddress);
                repository.save(item);
                return Result.successResult();
            }
        }
        repository.save(item);
        return Result.successResult();
    }
    @Transactional
    public Result deleteByUserId(Long userId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        repository.deleteByUserId(userId);
        return Result.successResult();
    }
    @Transactional
    public Result setDefault(Long addressId, Long userId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        if(addressId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ADDRESS);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        CustomerAddress address = repository.findByUserIdAndIsDefault(userId, 1);
        if (address == null) {
            return Result.failureResult(ExceptionCode.Failure.NOT_ADDRESSPO);
        }
        address.setIsDefault(0);
        repository.save(address);
        address = repository.getOne(addressId);
        address.setIsDefault(1);
        repository.save(address);
        return Result.successResult();
    }
    public Result deleteAddrsses(List<CustomerAddress> customerAddresses){
        repository.deleteAll(customerAddresses);
        return Result.successResult();
    }
}
