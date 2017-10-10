package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessaoTest {
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala("SalaCamila");
		
		Sessao sessao = new Sessao(horario, filme, sala);
		
		List<Sessao> sessoes = Arrays.asList(sessao);
		
		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciadorDeSessao.cabe(sessao));
	}
	@Test
	public void garanteQueNãoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente(){
		
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala("SalaCamila");
		
		List<Sessao> sessoes = Arrays.asList(new Sessao(horario,filme,sala));
		
		Sessao sessao = new Sessao(horario.minusHours(1), filme, sala);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
		
		
	}
	
	public void garanteQueNãoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente(){
		
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		Sala sala = new Sala("SalaCamila");
		
		List<Sessao> sessoesDaSala = Arrays.asList(new Sessao(horario,filme,sala));
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);

		Sessao sessao = new Sessao(horario.plusHours(1), filme, sala);
		
		Assert.assertFalse(gerenciador.cabe(sessao));
		
	}
	
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		Sala sala = new Sala("SalaCamila");
		
		Filme filme1 = new Filme("Rogue One", Duration.ofMinutes(90),"SCI-FI");
		LocalTime dezHoras = LocalTime.parse("10:00:00");
		Sessao sessaoDasDez = new Sessao(dezHoras, filme1, sala);
		
		Filme filme2 = new Filme("Dory", Duration.ofMinutes(120),"SCI-FI");
		LocalTime dezoitoHoras = LocalTime.parse("18:00:00");
		Sessao sessaoDasDezoito = new Sessao(dezoitoHoras, filme2, sala);
		
		List<Sessao> sessoes = Arrays.asList(sessaoDasDez, sessaoDasDezoito);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);

		Filme filme3 = new Filme("A Múmia", Duration.ofMinutes(60),"SCI-FI");
		LocalTime trezeHoras = LocalTime.parse("13:00:00");
		Sessao sessaoDasTreze = new Sessao(trezeHoras, filme3, sala);
	
		Assert.assertTrue(gerenciador.cabe(sessaoDasTreze));
		
	}
}
