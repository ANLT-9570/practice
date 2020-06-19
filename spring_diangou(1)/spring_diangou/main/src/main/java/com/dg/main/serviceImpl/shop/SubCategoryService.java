package com.dg.main.serviceImpl.shop;

import com.dg.main.Entity.Tree;
import com.dg.main.Entity.shop.Category;
import com.dg.main.Entity.shop.SubCategory;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.shop.CategoryRepository;
import com.dg.main.repository.shop.SubCategoryRepository;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import com.dg.main.vo.SubCategoryVo;
import io.reactivex.Observable;
import io.reactivex.Observer;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepository repository;
    @Autowired
    CategoryRepository categoryRepository;


    public Observable<List<SubCategory>> getList(Long id){
        return Observable.defer(()->{
            return Observable.fromArray(repository.findByParentId(id));
        });
    }
    public Result findByParentId(Long id){
        if(id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
       List<SubCategory> subCategories= getList(id).blockingFirst();
       return Result.successResult(subCategories);
    }

    public Result loadTree() {
        List<Category> list = categoryRepository.findAll();
        List<Tree> trees = Lists.newArrayList();
        for(Category category:list){
            Tree tree = new Tree();
            tree.setName(category.getName());
            tree.setId(String.valueOf(category.getId()));
            trees.add(tree);
        }
        return Result.successResult(trees);
    }

    public Result add(SubCategory subCategory) {
        repository.save(subCategory);
        return Result.successResult();
    }

    public slzcResult pageAll(Integer offset,Integer limit) {
        List<SubCategory> list = repository.findAllPage(offset,limit);
        List<SubCategoryVo> subCategoryVos = Lists.newArrayList();
        for(SubCategory subCategory:list){
            Optional<Category> optional = categoryRepository.findById(subCategory.getParentId());
            SubCategoryVo subCategoryVo = new SubCategoryVo();
            subCategoryVo.setPname(optional.get().getName());
            BeanUtils.copyProperties(subCategory,subCategoryVo);
            subCategoryVos.add(subCategoryVo);
        }
        Long total = repository.findAllCount();
        slzcResult slzcResult = new slzcResult();
        slzcResult.setRows(subCategoryVos);
        slzcResult.setTotal(total);
        return slzcResult;
    }

    public Result del(List<Long> ids) {
        for(Long id:ids){
            repository.deleteById(id);
        }
        return Result.successResult();
    }

    public Result findById(Long id) {
        if(id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Optional<SubCategory> optional = repository.findById(id);
        return Result.successResult(optional.get());
    }

    public Result deleteById(Long id) {
        repository.deleteById(id);
        return Result.successResult();
    }

    @Transactional
    public Result deleteByParentId(Long id) {
        repository.deleteByParentId(id);
        return Result.successResult();
    }

    public Result save(SubCategory subCategory) {
        if(subCategory.getParentId() == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_PARENTID);
        }
        repository.save(subCategory);
        return Result.successResult();
    }

    public Result update(SubCategory subCategory) {
        repository.save(subCategory);
        return Result.successResult();
    }

    public slzcResult findByParentIdList(Long id, Integer offset, Integer limit) {
        List<SubCategory> list = repository.findByParentIdList(id,offset,limit);
        Long subCount = repository.findByParentIdListCount(id);
        slzcResult slzcResult = new slzcResult();
        slzcResult.setRows(list);
        slzcResult.setTotal(subCount);
        return slzcResult;
    }
}
