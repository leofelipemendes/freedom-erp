package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PvcaixaId generated by hbm2java
 */
@Embeddable
public class PvcaixaId implements java.io.Serializable {

	private int codcaixa;
	private short codfilial;
	private int codemp;

	public PvcaixaId() {
	}

	public PvcaixaId(int codcaixa, short codfilial, int codemp) {
		this.codcaixa = codcaixa;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "CODCAIXA", nullable = false)
	public int getCodcaixa() {
		return this.codcaixa;
	}

	public void setCodcaixa(int codcaixa) {
		this.codcaixa = codcaixa;
	}

	@Column(name = "CODFILIAL", nullable = false)
	public short getCodfilial() {
		return this.codfilial;
	}

	public void setCodfilial(short codfilial) {
		this.codfilial = codfilial;
	}

	@Column(name = "CODEMP", nullable = false)
	public int getCodemp() {
		return this.codemp;
	}

	public void setCodemp(int codemp) {
		this.codemp = codemp;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PvcaixaId))
			return false;
		PvcaixaId castOther = (PvcaixaId) other;

		return (this.getCodcaixa() == castOther.getCodcaixa())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodcaixa();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
