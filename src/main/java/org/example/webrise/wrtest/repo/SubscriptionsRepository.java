package org.example.webrise.wrtest.repo;

import org.example.webrise.wrtest.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query(nativeQuery = true, value = "select s.* from subscribtions s where s.sub_name=:name and s.user_id=:id")
    Optional<SubscriptionEntity> findBySubNameAndUserEntity(@Param("name") String name, @Param("id") Long id);

    @Query(nativeQuery = true, value = "select s.* from subscribtions s where s.user_id=:userId")
    List<SubscriptionEntity> findByUserEntity(@Param("userId") Long userId);

}
