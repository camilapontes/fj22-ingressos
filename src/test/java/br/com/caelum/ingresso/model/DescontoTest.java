package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.descontos.DescontoDeTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {
	
	@Test
	public void deveConcederDescontoDe30PorcentroParaIngressosDeClientesDeBanco(){
		
		Lugar lugar = new Lugar("A", 1);
		
		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("20.5"));
		
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe50PorcentroParaIngressosDeEstudante(){
		Lugar lugar = new Lugar("A", 1);
		
		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("20.5"));
		
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal(){
		Lugar lugar = new Lugar("A", 1);
		
		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("20.5"));
		
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		
		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		
		BigDecimal precoEsperado = new BigDecimal("32.5");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
}
