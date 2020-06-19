package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size,Long>, JpaSpecificationExecutor<Size> {
        List<Size> findBySpecificationsId(Long specificationsId);
        Size findBySpecificationsIdAndColourAndDimensions(Long specificationsId,String colour,String dimensions);
}
