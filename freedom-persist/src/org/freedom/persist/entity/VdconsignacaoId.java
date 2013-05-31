package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VdconsignacaoId generated by hbm2java
 */
@Embeddable
public class VdconsignacaoId implements java.io.Serializable {

	private int codconsig;
	private short codfilial;
	private int codemp;

	public VdconsignacaoId() {
	}

	public VdconsignacaoId(int codconsig, short codfilial, int codemp) {
		this.codconsig = codconsig;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "CODCONSIG", nullable = false)
	public int getCodconsig() {
		return this.codconsig;
	}

	public void setCodconsig(int codconsig) {
		this.codconsig = codconsig;
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
		if (!(other instanceof VdconsignacaoId))
			return false;
		VdconsignacaoId castOther = (VdconsignacaoId) other;

		return (this.getCodconsig() == castOther.getCodconsig())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodconsig();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
