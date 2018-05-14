package com.plugyourcar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.plugyourcar.backend.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

}
