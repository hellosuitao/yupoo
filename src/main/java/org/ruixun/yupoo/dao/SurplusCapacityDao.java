package org.ruixun.yupoo.dao;

import org.ruixun.yupoo.bean.SurplusCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
/*
* 作者：suitao*/
public interface SurplusCapacityDao extends JpaRepository<SurplusCapacity,Long> {/*剩余容量类*/

    SurplusCapacity findSurplusCapacityById(Long id);/*根据用户id查剩余容量*/

    @Query("select s.surplusCapacity from SurplusCapacity s where s.id=:id")
    Optional<SurplusCapacity> findById(Long id);/**/

    @Modifying
    @Query("update SurplusCapacity s set s.surplusCapacity=s.surplusCapacity-?1 where s.id = ?2")
    void subCapacity(Long totalSize,Long id);/*根据id 减容量*/

}
