package s4.spring.td5.repositoties;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td5.entities.Category;
import s4.spring.td5.entities.User;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findOneById(int id);
}
