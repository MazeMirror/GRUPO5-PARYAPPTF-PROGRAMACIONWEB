package pe.edu.upc.service;



import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Comment;



public interface ICommentService {
	public boolean insert(Comment comment);
	public void delete(int idComment);
	public boolean update(Comment comment);
	public Optional<Comment> listId(int idComment);
	List<Comment> list();

}
