package org.freedom.persist.entity;

// Generated 31/05/2013 12:00:37 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pebatidalog generated by hbm2java
 */
@Entity
@Table(name = "PEBATIDALOG")
public class Pebatidalog implements java.io.Serializable {

	private PebatidalogId id;
	private char tipolog;
	private char tipobat;
	private Date dtins;
	private Date hins;
	private String idusuins;
	private Date dtalt;
	private Date halt;
	private String idusualt;
	private String idusulog;
	private Date dtinslog;
	private Date hinslog;

	public Pebatidalog() {
	}

	public Pebatidalog(PebatidalogId id, char tipolog, char tipobat,
			Date dtins, Date hins, String idusuins, Date dtalt, Date halt,
			String idusualt, String idusulog, Date dtinslog, Date hinslog) {
		this.id = id;
		this.tipolog = tipolog;
		this.tipobat = tipobat;
		this.dtins = dtins;
		this.hins = hins;
		this.idusuins = idusuins;
		this.dtalt = dtalt;
		this.halt = halt;
		this.idusualt = idusualt;
		this.idusulog = idusulog;
		this.dtinslog = dtinslog;
		this.hinslog = hinslog;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "hbat", column = @Column(name = "HBAT", nullable = false, length = 8)),
			@AttributeOverride(name = "dtbat", column = @Column(name = "DTBAT", nullable = false, length = 10)),
			@AttributeOverride(name = "seqlog", column = @Column(name = "SEQLOG", nullable = false)),
			@AttributeOverride(name = "codfilial", column = @Column(name = "CODFILIAL", nullable = false)),
			@AttributeOverride(name = "codemp", column = @Column(name = "CODEMP", nullable = false)),
			@AttributeOverride(name = "matempr", column = @Column(name = "MATEMPR", nullable = false)),
			@AttributeOverride(name = "codempep", column = @Column(name = "CODEMPEP", nullable = false)),
			@AttributeOverride(name = "codfilialep", column = @Column(name = "CODFILIALEP", nullable = false)) })
	public PebatidalogId getId() {
		return this.id;
	}

	public void setId(PebatidalogId id) {
		this.id = id;
	}

	@Column(name = "TIPOLOG", nullable = false, length = 1)
	public char getTipolog() {
		return this.tipolog;
	}

	public void setTipolog(char tipolog) {
		this.tipolog = tipolog;
	}

	@Column(name = "TIPOBAT", nullable = false, length = 1)
	public char getTipobat() {
		return this.tipobat;
	}

	public void setTipobat(char tipobat) {
		this.tipobat = tipobat;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTINS", nullable = false, length = 10)
	public Date getDtins() {
		return this.dtins;
	}

	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "HINS", nullable = false, length = 8)
	public Date getHins() {
		return this.hins;
	}

	public void setHins(Date hins) {
		this.hins = hins;
	}

	@Column(name = "IDUSUINS", nullable = false, length = 128)
	public String getIdusuins() {
		return this.idusuins;
	}

	public void setIdusuins(String idusuins) {
		this.idusuins = idusuins;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTALT", nullable = false, length = 10)
	public Date getDtalt() {
		return this.dtalt;
	}

	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "HALT", nullable = false, length = 8)
	public Date getHalt() {
		return this.halt;
	}

	public void setHalt(Date halt) {
		this.halt = halt;
	}

	@Column(name = "IDUSUALT", nullable = false, length = 128)
	public String getIdusualt() {
		return this.idusualt;
	}

	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}

	@Column(name = "IDUSULOG", nullable = false, length = 128)
	public String getIdusulog() {
		return this.idusulog;
	}

	public void setIdusulog(String idusulog) {
		this.idusulog = idusulog;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTINSLOG", nullable = false, length = 10)
	public Date getDtinslog() {
		return this.dtinslog;
	}

	public void setDtinslog(Date dtinslog) {
		this.dtinslog = dtinslog;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HINSLOG", nullable = false, length = 10)
	public Date getHinslog() {
		return this.hinslog;
	}

	public void setHinslog(Date hinslog) {
		this.hinslog = hinslog;
	}

}
