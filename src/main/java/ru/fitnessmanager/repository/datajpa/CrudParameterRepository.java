package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.Parameter;

import java.util.List;

@Transactional(readOnly = false)
public interface CrudParameterRepository extends JpaRepository<Parameter, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Parameter p WHERE p.id=:id")
    int delete(@Param("id") int id);

    Parameter save(Parameter parameter);

    @Query("SELECT p FROM Parameter p")
    List<Parameter> getAll();
}
