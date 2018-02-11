package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.UserParameters;

import java.util.List;

@Transactional(readOnly = false)
public interface CrudUserParametersRepository extends JpaRepository<UserParameters, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserParameters up WHERE up.id=:id AND up.user.id=:userId AND up.parameter.id=:parameterId")
    int delete(@Param("id") int id, @Param("userId") int userId, @Param("parameterId") int parameterId);

    UserParameters save(UserParameters userParameters);

    @Query("SELECT up FROM UserParameters up WHERE up.user.id=:userId AND up.parameter.id=:parameterId")
    List<UserParameters> getAll(@Param("userId") int userId, @Param("parameterId") int parameterId);
}
