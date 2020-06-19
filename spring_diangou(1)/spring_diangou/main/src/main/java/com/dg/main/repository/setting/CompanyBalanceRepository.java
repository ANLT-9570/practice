package com.dg.main.repository.setting;

import com.dg.main.Entity.settings.CompanyBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyBalanceRepository extends JpaRepository<CompanyBalance,Long> {
    @Modifying
    @Query(nativeQuery = true,value = "update company_balance set version=version+1,money=money+ :money,modify=now() where id= :id and version= :version")
    Integer increaseVersionUpdateMoney(@Param("version")Long version, @Param("money")Long money, @Param("id")Long id);
    @Modifying
    @Query(nativeQuery = true,value = "update company_balance set version=version+1,platform_money=platform_money+#{PlatformMoney},modify=now() where id=#{id} and version=#{version}")
    Integer increaseVersionUpdatePlatformMoney(@Param("version")Long version,@Param("PlatformMoney")Long PlatformMoney,@Param("id")Long i);
    @Modifying
    @Query(nativeQuery = true,value = "update company_balance set version=version+1,money=money-#{money},modify=now() where id=#{id} and version=#{version}")
    Integer decreaseVersionUpdateMoney(@Param("version")Long version,@Param("money")Long money,@Param("id")Long id);
    @Modifying
    @Query(nativeQuery = true,value = "update company_balance set version=version+1,platform_money=platform_money-#{PlatformMoney},modify=now() where id=#{id} and version=#{version}")
    Integer decreaseVersionUpdatePlatformMoney(@Param("version")Long version,@Param("PlatformMoney")Long PlatformMoney,@Param("id")Long id);
    CompanyBalance findByTypes(Integer types);
}
