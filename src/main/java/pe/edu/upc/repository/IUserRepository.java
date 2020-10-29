package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	@Query("from User u where u.nameUser like %:nameUser%")
	List<User> buscarNombre(@Param("nameUser") String nameUser);
}
