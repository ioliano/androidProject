package com.mymeds.objects;

import java.util.Date;

/**
 * Created by daniel on 25/03/2017.
 */

public class RlUtilizadorMedicamento {


    private Long idRlUtilizadorMedicamento;
    private Utilizador utilizador;
    private Medicamento medicamento;
    private Date dateInicioDosagem;
    private Date dateFimDosagem;
    private Integer numeroDoses;
    private Integer horasIntervalo;
    private Integer alarme;
    private String createdBy;
    private String updatedBy;
    private Date creationDate;
    private Date updateDate;
    private Integer enabled;
    private Date dataUltimaToma;
    private Long idAlarme;
    

    public RlUtilizadorMedicamento() {
    }

    public Long getIdRlUtilizadorMedicamento() {
        return idRlUtilizadorMedicamento;
    }

    public void setIdRlUtilizadorMedicamento(Long idRlUtilizadorMedicamento) {
        this.idRlUtilizadorMedicamento = idRlUtilizadorMedicamento;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Date getDateInicioDosagem() {
        return dateInicioDosagem;
    }

    public void setDateInicioDosagem(Date dateInicioDosagem) {
        this.dateInicioDosagem = dateInicioDosagem;
    }

    public Date getDateFimDosagem() {
        return dateFimDosagem;
    }

    public void setDateFimDosagem(Date dateFimDosagem) {
        this.dateFimDosagem = dateFimDosagem;
    }

    public Integer getNumeroDoses() {
        return numeroDoses;
    }

    public void setNumeroDoses(Integer numeroDoses) {
        this.numeroDoses = numeroDoses;
    }

    public Integer getHorasIntervalo() {
        return horasIntervalo;
    }

    public void setHorasIntervalo(Integer horasIntervalo) {
        this.horasIntervalo = horasIntervalo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

	public Integer getAlarme() {
		return alarme;
	}

	public void setAlarme(Integer alarme) {
		this.alarme = alarme;
	}

	public Date getDataUltimaToma() {
		return dataUltimaToma;
	}

	public void setDataUltimaToma(Date dataUltimaToma) {
		this.dataUltimaToma = dataUltimaToma;
	}

	public Long getIdAlarme() {
		return idAlarme;
	}

	public void setIdAlarme(Long idAlarme) {
		this.idAlarme = idAlarme;
	}
	
}
