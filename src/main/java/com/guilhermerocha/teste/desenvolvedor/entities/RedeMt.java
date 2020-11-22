package com.guilhermerocha.teste.desenvolvedor.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_rede_mt")
public class RedeMt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,unique = true )
	private String codigo;

	@Column(nullable=false)
	private String nome;
	
	@Column(nullable = false)
	private Double tensaoNominal;
	
	@ManyToOne
	@JoinColumn(name = "id_subestacao", nullable = false )
	private Subestacao subestacao;
	
	public RedeMt() {}

	public RedeMt(Long id, String codigo, String nome, Double tensaoNominal) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.tensaoNominal = tensaoNominal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getTensaoNominal() {
		return tensaoNominal;
	}

	public void setTensaoNominal(Double tensaoNominal) {
		this.tensaoNominal = tensaoNominal;
	}

	public Subestacao getSubestacao() {
		return subestacao;
	}

	public void setSubestacao(Subestacao subestacao) {
		this.subestacao = subestacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedeMt other = (RedeMt) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
