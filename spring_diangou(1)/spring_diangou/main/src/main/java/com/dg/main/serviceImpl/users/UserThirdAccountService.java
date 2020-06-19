package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.User;
import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.cache_service.orders.UserThirdAccountCacheService;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.repository.users.UserThirdAccountRepository;
//import com.dg.main.repository.orders.UserThirdAccountRepository;
import com.dg.main.util.Result;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserThirdAccountService {
//    @Autowired
//    UserThirdAccountMapper userThirdAccountMapper;
    @Autowired
    UserThirdAccountCacheService cacheService;
//    @Autowired
//    UserThirdAccountRepository userThirdAccountMapper;
    @Autowired
    UserThirdAccountRepository repository;
    @Autowired
    CustomerAddressRepository addressRepository;
    public UserThirdAccount findBy(Long id) {

        return repository.getOne(id);
    }

    public UserThirdAccount findByUserId(Long id){
        //return cacheService.get(id);
        return repository.findByUserId(id);
    }
    public Result updateZhifubao(UserThirdAccount userThirdAccount){
        UserThirdAccount account=repository.findByUserId(userThirdAccount.getUserId());
        account.setZhifubaoPayeeRealName(userThirdAccount.getZhifubaoPayeeRealName());
        account.setZhifubaoPayeeAccount(userThirdAccount.getZhifubaoPayeeAccount());
        cacheService.update(account);
        return Result.successResult();
    }
    public Result updateWeixi(UserThirdAccount userThirdAccount){
        UserThirdAccount account=repository.findByUserId(userThirdAccount.getUserId());
        account.setWeixinOpenId(userThirdAccount.getWeixinOpenId());
        account.setWeixinRealName(userThirdAccount.getWeixinRealName());
        cacheService.update(account);
        return Result.successResult();
    }
    public void deleteBy(Long id){
        repository.deleteById(id);
    }


    public void update(UserThirdAccount t) {

         cacheService.update(t);
    }


    public void save(UserThirdAccount t) {

         cacheService.save(t);
    }
    @Transactional
    public Single rxUpdate(final UserThirdAccount userThirdAccount){
        return Single.create(new SingleOnSubscribe<UserThirdAccount>() {
            @Override
            public void subscribe(SingleEmitter<UserThirdAccount> emitter) throws Exception {
                emitter.onSuccess(repository.save(userThirdAccount));
            }
        });
    }
    @Transactional
    public Single rxSave(final  UserThirdAccount userThirdAccount){
        return Single.create(new SingleOnSubscribe<UserThirdAccount>() {
            @Override
            public void subscribe(SingleEmitter<UserThirdAccount> emitter) throws Exception {
                emitter.onSuccess(repository.save(userThirdAccount));
            }
        });
    }
    public Observable findOne(){
        return Observable.defer(()->Observable.just(repository.getOne(1l)));
    }
    public Single findOne1(){
        return Single.create(new SingleOnSubscribe<UserThirdAccount>() {
            @Override
            public void subscribe(SingleEmitter<UserThirdAccount> emitter) throws Exception {
               emitter.onSuccess(repository.getOne(277l));
            }
        });
    }
    public Observable findAddress(){
        return Observable.defer(()->{
            return  Observable.just(addressRepository.getOne(1l));
        });
    }
    public List<UserThirdAccount> selectAll(UserThirdAccount t) {
        // TODO Auto-generated method stub
        return null;
    }
    @Transactional
    public Result rxUpdate(){
//        Observable.merge(findOne(),findAddress()).
//       UserThirdAccount userThirdAccount= (UserThirdAccount) (findOne1().to(new Function() {
//            @Override
//            public Object apply(Object o) throws Exception {
//                return o;
//            }
//        }));
//        findOne1().to(new Function<UserThirdAccount,UserThirdAccount>() {
//            @Override
//            public UserThirdAccount apply(UserThirdAccount o) throws Exception {
//                return o;
//            }
//        }).toString();
     //   findOne1().blockingGet().toString();
       // UserThirdAccount userThirdAccount=(UserThirdAccount)findOne1().blockingGet();
//        findOne1().cast(UserThirdAccount.class).toString();
//       // System.out.println(userThirdAccount);

        return Result.successResult();
//        findOne().map(new Function<UserThirdAccount, UserThirdAccount>(){
//
//            @Override
//            public UserThirdAccount apply(UserThirdAccount userThirdAccount) throws Exception {
//                userThirdAccount.setWeixinOpenId("fwefwefewfew");
//                return userThirdAccount;
//            }
//        }).
    }

    //分页

//    public slzcResult selectAll(int offset, int limit) {
//
//        slzcResult result = new slzcResult();
//
//        int count = userThirdAccountMapper.selectCount();
//
////		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
//        List<UserThirdAccount> businesses = userThirdAccountMapper.selectPageAll( offset,limit);
//
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////		for (Business business : businesses) {
////			Timestamp createtime = business.getCreatetime();
////			Timestamp modifytime = business.getModifytime();
////			format.format(createtime.getTime());
////
////		}
//        result.setRows(businesses);
//        result.setTotal(count);
//
//        return result;
//    }


    public List<UserThirdAccount> selectPageAll(int offset, int limit) {

        return null;
    }


//    public void deleteAllId(List<UserThirdAccount> t) {
//
//        userThirdAccountMapper.deleteInBatch(t);
//    }



//    public int selectCount() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
}
