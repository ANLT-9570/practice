package com.dg.main.dao;


import java.util.List;

import com.dg.main.Entity.orders.RedPack;
import org.springframework.stereotype.Repository;

@Repository
public interface AppredpackMapper {
	public List<RedPack> selectAppredpack(RedPack redPack);
	

}
