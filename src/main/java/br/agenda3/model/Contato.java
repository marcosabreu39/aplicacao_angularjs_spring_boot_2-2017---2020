package br.agenda3.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@CustomContatoValidator
@Component
@Entity
public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_id_seq")
	@SequenceGenerator(name = "contato_id_seq", sequenceName = "contato_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório.")
	@Size(min = 4, max = 40, message = "Mínimo de 4 e máximo de 40 caracteres.")
	private String nome;

	@Column(nullable = true, unique = true)
	@Pattern(regexp = "^$|^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})$", message = "Insira um e-mail válido.")
	private String email;

	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório.")
	@Pattern(regexp = "^$|^\\d{10,11}$", message = "Insira um telefone válido.")
	private String telefone;

	@Column(nullable = true)
	@Size(max = 60, message = "Máximo de 60 caracteres.")
	private String endereco;

	@Column(nullable = true)
	@Size(max = 200, message = "Máximo de 200 caracteres.")
	private String observacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
