package ru.fitnessmanager.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.fitnessmanager.model.User;
import ru.fitnessmanager.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository{

    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private CrudUserRepository crudRepository;


    public User save(User user) {
        return crudRepository.save(user);
    }


    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }


    public User get(int id) {
        return crudRepository.findOne(id);
    }


    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }


    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }
}
