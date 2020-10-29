package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Event;

@Repository
public interface IEventRepository extends JpaRepository<Event, Integer> {
	
	@Query("from Event e where e.nameEvent like %:nameEvent%")
	List<Event> findName(@Param("nameEvent") String nameEvent);
}
