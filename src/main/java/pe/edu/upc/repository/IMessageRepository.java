package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Message;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer>{
	@Query("from Message m where m.subject like %:subject%")
	List<Message> buscarAsunto(@Param("subject") String subject);
	
	@Query("from Message m where m.user.nameUser like %:nameUser%")
	List<Message> buscarUsuario(@Param("nameUser") String nameUser);
}