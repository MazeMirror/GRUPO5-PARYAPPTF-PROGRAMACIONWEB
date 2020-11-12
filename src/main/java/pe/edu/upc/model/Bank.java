package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Bank")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBank;

	@NotEmpty(message = "Debe ingresar un banco")
	@NotBlank(message = "Debe ingresar un banco")
	@Column(name = "nombreBank", length = 100, nullable = false)
	private String nameBank;

	public int getIdBank() {
		return idBank;
	}

	public void setIdBank(int idBank) {
		this.idBank = idBank;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}
	
	

}
