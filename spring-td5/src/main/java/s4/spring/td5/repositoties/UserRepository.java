package s4.spring.td5.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td5.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findOneByLogin(String login);

}
