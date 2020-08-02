package br.agenda3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@CustomUsuarioValidator
@Entity
@Component
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8238587905744175289L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório.")
	@Size(min = 4, max = 30, message = "Mínimo de 4 e máximo de 30 caracteres.")
	private String nome;

	@Column(nullable = false, unique = true)
	@NotNull(message = "Campo obrigatório.")
	@Pattern(regexp = "^([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})$", message = "Insira um e-mail válido.")
	private String email;

	@Column(unique = true, nullable = false)
	@NotNull(message = "Campo obrigatório.")
	@Size(min = 4, max = 12, message = "Mínimo de 4 e máximo de 12 caracteres.")
	private String login;

	@Column(nullable = false)
	@NotNull(message = "Campo obrigatório.")
	@Size(min = 4, message = "Mínimo de 4 caracteres.")
	private String senha;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataCadastro;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Contato> contatos = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "USUARIO_PRIVILEGIO", joinColumns = { @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "PRIVILEGIO_ID", referencedColumnName = "ID") })
	private Set<Privilegio> privilegios = new HashSet<>();

	@Column
	private boolean habilitado = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Set<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(Set<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
}
