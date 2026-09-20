    package com.cp.airsync.repository;


    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import com.cp.airsync.entity.User;

    @Repository
    public interface UserRepository extends JpaRepository<User,Long> {

    }
