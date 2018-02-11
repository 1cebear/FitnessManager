package ru.fitnessmanager.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.fitnessmanager.model.Category;

import java.util.List;

@Transactional(readOnly = false)
public interface CrudCategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.id=:id")
    int delete(@Param("id") int id);

    Category save(Category category);

    @Query("SELECT c FROM Category c")
    List<Category> getAll();
}
