package s4.spring.td5.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td5.entities.Language;
import s4.spring.td5.entities.User;

public interface LanguageRepository extends JpaRepository<Language, Integer>{
	Language findOneById(int id);
}
