package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AtconveniadoId generated by hbm2java
 */
@Embeddable
public class AtconveniadoId implements java.io.Serializable {

	private int codconv;
	private short codfilial;
	private int codemp;

	public AtconveniadoId() {
	}

	public AtconveniadoId(int codconv, short codfilial, int codemp) {
		this.codconv = codconv;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "CODCONV", nullable = false)
	public int getCodconv() {
		return this.codconv;
	}

	public void setCodconv(int codconv) {
		this.codconv = codconv;
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
		if (!(other instanceof AtconveniadoId))
			return false;
		AtconveniadoId castOther = (AtconveniadoId) other;

		return (this.getCodconv() == castOther.getCodconv())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodconv();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
