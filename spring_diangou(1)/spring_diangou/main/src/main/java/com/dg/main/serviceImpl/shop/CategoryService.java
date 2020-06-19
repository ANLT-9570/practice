package com.dg.main.serviceImpl.shop;

import com.dg.main.Entity.shop.Category;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.shop.CategoryRepository;
//import com.dg.main.repository.orders.CategoryRepository;

import com.dg.main.repository.shop.SubCategoryRepository;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import io.reactivex.Observable;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
//    @Autowired
//    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;
    public Category findBy(Long id) {

        return categoryRepository.getOne(id);
    }

    public List<Category> findAll(){
      return categoryRepository.findAll();
    }
    public Result findAll1(){
        return Result.successResult(rxGetAll().blockingFirst()) ;
    }

    private Observable<List<Category>> rxGetAll(){
        return Observable.defer(()->Observable.fromArray(categoryRepository.findAll()));
    }

    public void deleteBy(Long id){
        categoryRepository.deleteById(id);
    }


    public void update(Category t) {

        categoryRepository.save(t);
    }


    public void save(Category t) {

        categoryRepository.save(t);
    }



    public List<Category> selectAll(Category t) {
        // TODO Auto-generated method stub
        return null;
    }


    //分页

    public slzcResult selectAll(int offset, int limit) {

        slzcResult result = new slzcResult();

     //   int count = categoryMapper.selectCount();

//		List<Business> businesses = businessMapper.selectAll(limit, offset,null);
     //   List<Category> businesses = categoryMapper.selectPageAll( offset,limit);

//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//		for (Business business : businesses) {
//			Timestamp createtime = business.getCreatetime();
//			Timestamp modifytime = business.getModifytime();
//			format.format(createtime.getTime());
//
//		}
     //   result.setRows(businesses);
    //    result.setTotal(count);

        return result;
    }


    public List<Category> selectPageAll(int offset, int limit) {

        return null;
    }


    public void deleteAllId(List<Category> t) {

        categoryRepository.deleteInBatch(t);
    }

    public slzcResult findAllPage(Integer offset,Integer limit){
        List<Category> categories = categoryRepository.findAllLimit(offset,limit);
        Long total = categoryRepository.findAllCount();
        slzcResult result = new slzcResult();
        result.setTotal(total);
        result.setRows(categories);
        return result;
    }
    public void delete(Long[] ids) {
        for(Long id:ids){
            categoryRepository.deleteById(id);
        }
    }


    public Result findById(Long id) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Category> optional = categoryRepository.findById(id);
        return Result.successResult(optional.get());
    }


    public Result deleteById(Long id) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        categoryRepository.deleteById(id);
        subCategoryRepository.deleteByParentId(id);
        return Result.successResult();
    }


}
