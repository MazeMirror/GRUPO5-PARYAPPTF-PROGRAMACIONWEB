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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

	@Entity
	@Table(name="Card")
	public class Card implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int idCard;
		
		@ManyToOne
		@JoinColumn(name="idUser", nullable = false)
		private User user;
		
		@ManyToOne
		@JoinColumn(name="idBank", nullable = false)
		private Bank bank;
		
		@NotEmpty(message="Debe ingresar un titular")
		@NotBlank(message="Debe ingresar un titular")
		@Column(name="nombreCards", length=60, nullable=false)
		private String nameCard;
		
		@NotNull
		@Future(message = "Debe ser una fecha futura")
		@Temporal(TemporalType.DATE)
		@Column(name="fechavencimiento")
		@DateTimeFormat(pattern = "yyyy-MM-dd")			
		private Date fechadevencimiento;
		
		@NotEmpty(message="Debe ingresar un numero de tarjeta")
		@NotBlank(message="Debe ingresar un numero de tarjeta")
		@Digits(integer = 16, fraction = 0)
		@Size(min = 16, max = 16, message = "Debe contener 16 digitos")
		@Column(name="numerotarjeta", length=16, nullable=false)
        private String numerotarjeta;
    
		@NotEmpty(message="Debe ingresar un numero de cvv")
		@NotBlank(message="Debe ingresar un numero de cvv")
		@Digits(integer = 3, fraction = 0)
		@Size(min = 3, max = 3, message = "Debe contener 3 digitos")
		@Column(name="cvv", length=3, nullable=false)
        private String cvv;

		public int getIdCard() {
			return idCard;
		}

		public void setIdCard(int idCard) {
			this.idCard = idCard;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Bank getBank() {
			return bank;
		}

		public void setBank(Bank bank) {
			this.bank = bank;
		}

		public String getNameCard() {
			return nameCard;
		}

		public void setNameCard(String nameCard) {
			this.nameCard = nameCard;
		}

		public Date getFechadevencimiento() {
			return fechadevencimiento;
		}

		public void setFechadevencimiento(Date fechadevencimiento) {
			this.fechadevencimiento = fechadevencimiento;
		}

		public String getNumerotarjeta() {
			return numerotarjeta;
		}

		public void setNumerotarjeta(String numerotarjeta) {
			this.numerotarjeta = numerotarjeta;
		}

		public String getCvv() {
			return cvv;
		}

		public void setCvv(String cvv) {
			this.cvv = cvv;
		}

		
}

