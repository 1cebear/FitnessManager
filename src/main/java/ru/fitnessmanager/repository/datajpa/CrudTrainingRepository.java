package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.Training;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = false)
public interface CrudTrainingRepository extends JpaRepository<Training, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Training t WHERE t.id=:id AND t.exercise.id=:exerciseId AND t.user.id=:userId")
    int delete(@Param("id") int id, @Param("user") int userId, @Param("exerciseId") int exerciseId);

    Training save(Training training);

    @Query("SELECT t FROM Training t WHERE t.exercise.id=:exerciseId AND t.user.id=:userId")
    List<Training> getAll(@Param("userId") int userId, @Param("exerciseId") int exerciseId);

    @Query("SELECT t FROM Training t WHERE t.exercise.id=:exerciseId AND t.user.id=:userId AND t.date BETWEEN :startDate AND :endDate ORDER BY t.date ASC")
    List<Training> getBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") int userId, @Param("exerciseId") int exerciseId);

}