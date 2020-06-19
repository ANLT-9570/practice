package com.dg.main.serviceImpl.users;

import java.util.List;

import com.dg.main.vo.MyCollectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dg.main.Entity.MyCollection;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.CollectionMapper;
import com.dg.main.service.CollectionServer;

@Service
public class CollectionServiceImpl implements CollectionServer{

	@Autowired
	private CollectionMapper collectionMapper;
	
	@Override
	public MyCollection selectOne(MyCollection t) {
		
		return collectionMapper.selectOne(t);
	}


	@Override
	public int delete(MyCollection t) {
		// TODO Auto-generated method stub
		collectionMapper.deleteBy(t.getId());
		return 0;
	}

	@Override
	public int update(MyCollection t) {
		
		return collectionMapper.update(t);
	}

	@Override
	public int save(MyCollection t) {
		
		return collectionMapper.save(t);
	}


	@Override
	public List<MyCollection> selectAll(MyCollection t) {
		// TODO Auto-generated method stub
		return null;
	}


	//分页
	@Override
	public slzcResult selectAll(int offset,int limit,String search) {
		
		slzcResult result = new slzcResult();
		
		int count = collectionMapper.selectCount(search);
		
		List<MyCollection> administrator = collectionMapper.selectPageAll( offset,limit,search);
		result.setRows(administrator);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public List<MyCollection> selectPageAll( int offset,int limit) {
		
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		
		return collectionMapper.deleteAllId(t);
	}


	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//我的收藏
	@Override
	public List<MyCollectionVo> findUserCollectionGoods(Integer uid, Integer role, Integer status, Integer offset, Integer limit) {
		List<MyCollectionVo> list = collectionMapper.findUserCollectionGoods(uid,role,status,offset,limit);
		for (MyCollectionVo collectionVo : list) {
//			String image = collectionVo.getImage();
//			String[] split = image.split("，");
//			if (split.length > 0) {
//				collectionVo.setImage(split[0]);
//			}
//			System.out.println(collectionVo.getImage());
		}
		return list;
	}


	@Override
	public slzcResult selectAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public MyCollection IsOrNoCollect(Long uid, Long cid,Long shopId) {
		return collectionMapper.IsOrNoCollect(uid,cid,shopId);
	}
	
	@Override
	public int CancelCollect(Long uid, Long cid,int status,Long shopId) {
		return collectionMapper.CancelCollect(uid,cid,status,shopId);
	}
	
	@Override
	public int addCollect(Long uid, Long cid,Long shopId) {
		return collectionMapper.addCollect(uid,cid,shopId);
	}
	
}
