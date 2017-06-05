package com.mymeds.objects;

import java.util.Date;

/**
 * Created by daniel on 25/03/2017.
 */

public class Processo {

    private Long idProcesso;
    private Integer numUtente;
    private Integer flgGenero;
    private Date dataNascimento;
    private Integer numBeneficiario;
    private Pais pais;
    private String createdBy;
    private String updatedBy;
    private Date creationDate;
    private Date updateDate;
    private Integer enabled;

    public Processo() {
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Integer getNumUtente() {
        return numUtente;
    }

    public void setNumUtente(Integer numUtente) {
        this.numUtente = numUtente;
    }

    public Integer getFlgGenero() {
        return flgGenero;
    }

    public void setFlgGenero(Integer flgGenero) {
        this.flgGenero = flgGenero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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

    public Integer getNumBeneficiario() {
        return numBeneficiario;
    }

    public void setNumBeneficiario(Integer numBeneficiario) {
        this.numBeneficiario = numBeneficiario;
    }

}
