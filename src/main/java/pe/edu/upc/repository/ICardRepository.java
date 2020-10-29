package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Card;



@Repository
public interface ICardRepository extends JpaRepository<Card, Integer>{
	@Query("from Card c where c.nameCard like %:nameCard%")
	List<Card> buscarCard(@Param("nameCard") String nameCard);
}