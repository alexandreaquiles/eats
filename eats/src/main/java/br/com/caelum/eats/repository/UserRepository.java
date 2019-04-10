package br.com.caelum.eats.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.eats.model.User;

public interface UserRepository extends Repository<User, Long> {

	Optional<User> findByName(String name);

	Optional<User> findById(Long userId);

}
