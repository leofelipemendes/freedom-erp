package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EqprocexpedId generated by hbm2java
 */
@Embeddable
public class EqprocexpedId implements java.io.Serializable {

	private int codtipoexped;
	private int codprocexped;
	private short codfilial;
	private int codemp;

	public EqprocexpedId() {
	}

	public EqprocexpedId(int codtipoexped, int codprocexped, short codfilial,
			int codemp) {
		this.codtipoexped = codtipoexped;
		this.codprocexped = codprocexped;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "CODTIPOEXPED", nullable = false)
	public int getCodtipoexped() {
		return this.codtipoexped;
	}

	public void setCodtipoexped(int codtipoexped) {
		this.codtipoexped = codtipoexped;
	}

	@Column(name = "CODPROCEXPED", nullable = false)
	public int getCodprocexped() {
		return this.codprocexped;
	}

	public void setCodprocexped(int codprocexped) {
		this.codprocexped = codprocexped;
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
		if (!(other instanceof EqprocexpedId))
			return false;
		EqprocexpedId castOther = (EqprocexpedId) other;

		return (this.getCodtipoexped() == castOther.getCodtipoexped())
				&& (this.getCodprocexped() == castOther.getCodprocexped())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodtipoexped();
		result = 37 * result + this.getCodprocexped();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
