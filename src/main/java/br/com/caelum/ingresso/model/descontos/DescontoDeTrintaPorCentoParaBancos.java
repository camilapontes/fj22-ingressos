package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentoParaBancos implements Desconto {

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentoSObre(precoOriginal));
	}
	
	public BigDecimal trintaPorCentoSObre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(new BigDecimal("0.3"));
	}

}
