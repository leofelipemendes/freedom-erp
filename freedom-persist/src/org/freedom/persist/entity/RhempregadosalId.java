package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RhempregadosalId generated by hbm2java
 */
@Embeddable
public class RhempregadosalId implements java.io.Serializable {

	private int matempr;
	private short seqsal;
	private short codfilial;
	private int codemp;

	public RhempregadosalId() {
	}

	public RhempregadosalId(int matempr, short seqsal, short codfilial,
			int codemp) {
		this.matempr = matempr;
		this.seqsal = seqsal;
		this.codfilial = codfilial;
		this.codemp = codemp;
	}

	@Column(name = "MATEMPR", nullable = false)
	public int getMatempr() {
		return this.matempr;
	}

	public void setMatempr(int matempr) {
		this.matempr = matempr;
	}

	@Column(name = "SEQSAL", nullable = false)
	public short getSeqsal() {
		return this.seqsal;
	}

	public void setSeqsal(short seqsal) {
		this.seqsal = seqsal;
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
		if (!(other instanceof RhempregadosalId))
			return false;
		RhempregadosalId castOther = (RhempregadosalId) other;

		return (this.getMatempr() == castOther.getMatempr())
				&& (this.getSeqsal() == castOther.getSeqsal())
				&& (this.getCodfilial() == castOther.getCodfilial())
				&& (this.getCodemp() == castOther.getCodemp());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMatempr();
		result = 37 * result + this.getSeqsal();
		result = 37 * result + this.getCodfilial();
		result = 37 * result + this.getCodemp();
		return result;
	}

}
