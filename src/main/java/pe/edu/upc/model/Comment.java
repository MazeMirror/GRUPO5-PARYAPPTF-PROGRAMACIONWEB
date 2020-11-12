package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComment;
	
	@ManyToOne
	@JoinColumn(name="idEvent", nullable = false)
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable = false)
	private User user;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="typecomment", length=20, nullable = false )
	private String typecomment;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="textcomment", length= 200, nullable = false )
	private String textcomment;

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTypecomment() {
		return typecomment;
	}

	public void setTypecomment(String typecomment) {
		this.typecomment = typecomment;
	}

	public String getTextcomment() {
		return textcomment;
	}

	public void setTextcomment(String textcomment) {
		this.textcomment = textcomment;
	}

	
	
}
