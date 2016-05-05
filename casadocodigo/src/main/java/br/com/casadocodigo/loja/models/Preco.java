package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import br.com.casadocodigo.loja.enums.TipoPreco;

@Embeddable				// Usado com @ElementCollection para fazer o relacionamento. Não tem representação no banco de dados.
public class Preco {
	private BigDecimal valor;
	private TipoPreco tipo;
	
	public BigDecimal getValor() {
		return valor;
	}	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.valor + " - " + this.tipo;
	}	
}