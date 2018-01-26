package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.WeightControl;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudWeightControlRepository extends JpaRepository<WeightControl, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM WeightControl wc WHERE wc.id=:id AND wc.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    WeightControl save(WeightControl weightControl);

    @Query("SELECT wc FROM WeightControl wc WHERE wc.user.id=:userId")
    List<WeightControl> getAll(@Param("userId") int userId);
}
