package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.model.Comment;
import pe.edu.upc.repository.ICommentRepository;
import pe.edu.upc.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService{
	
	@Autowired
	private ICommentRepository dComment;

	@Override
	@Transactional
	public boolean insert(Comment comment) {
		Comment objComment= dComment.save(comment);
		if(objComment == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean update(Comment comment) {
		boolean flag = false;
		try {
			dComment.save(comment);
			flag = true;
		} catch (Exception e) {
			System.out.println("Succedio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idComment) {
		dComment.deleteById(idComment);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Comment> listId(int idComment) {
		return dComment.findById(idComment);
	}

	@Override
	@Transactional
	public List<Comment> list() {
		return dComment.findAll();
	}


}
