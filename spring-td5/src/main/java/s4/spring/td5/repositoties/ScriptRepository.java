package s4.spring.td5.repositoties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.User;

public interface ScriptRepository extends JpaRepository<Script, Integer>{
	List<Script> findByUser(User user);
	Script findOneById(int id);
}
