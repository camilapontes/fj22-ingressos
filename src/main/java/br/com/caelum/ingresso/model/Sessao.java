package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
	@Id
	@GeneratedValue
	private Integer id;
	
	private LocalTime horario;
	
	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Filme filme;
	
	private BigDecimal preco;
	
	@Deprecated
	public Sessao(){
		
	}
	
	public Sessao(LocalTime horario, Filme filme, Sala sala) {
		this.horario=horario;
		this.setFilme(filme);
		this.sala=sala;
		this.preco = sala.getPreco().add(filme.getPreco());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public LocalTime getHorarioTermino(){
		return this.horario.plusMinutes(filme.getDuracao().toMinutes());
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Map<String, List<Lugar>> getMapaDeLugares(){
		return sala.getMapaDeLugares();
	}
	
	
	
}
