package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.User;

import java.util.List;

public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    User save(User user);

    @Query("Select u from User u where u.id=:id")
    User findOne(@Param("id") Integer id);


    List<User> findAll(Sort sort);

    User getByEmail(String email);
}
