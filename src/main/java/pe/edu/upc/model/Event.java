package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Event")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvent;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nameEvent", length=70, nullable = false )
	private String nameEvent;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable = false)
	private User user;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="fechaEvento")
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private Date dateEvent;
	
	@Column(name="numberTickets", length=5, nullable = false )
	private int numberTickets;
	
	@Column(name="priceTicket", length=3, nullable = false )
	private int priceTicket;
	
	@NotNull
	@Column(name = "startTime")
	private String startTime;
	
	@NotNull
	@Column(name = "endsTime")
	private String endsTime;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name = "descriptionEvent", length = 500, nullable = false )
	private String descriptionEvent;

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public int getNumberTickets() {
		return numberTickets;
	}

	public void setNumberTickets(int numberTickets) {
		this.numberTickets = numberTickets;
	}

	public int getPriceTicket() {
		return priceTicket;
	}

	public void setPriceTicket(int priceTicket) {
		this.priceTicket = priceTicket;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndsTime() {
		return endsTime;
	}

	public void setEndsTime(String endsTime) {
		this.endsTime = endsTime;
	}

	public String getDescriptionEvent() {
		return descriptionEvent;
	}

	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
