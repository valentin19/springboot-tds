package s4.spring.td2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.td2.entities.Organisation;

public interface OrgasRepository extends JpaRepository<Organisation, Integer>{
}
