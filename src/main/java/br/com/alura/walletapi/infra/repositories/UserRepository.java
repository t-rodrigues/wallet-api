package br.com.alura.walletapi.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.walletapi.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
