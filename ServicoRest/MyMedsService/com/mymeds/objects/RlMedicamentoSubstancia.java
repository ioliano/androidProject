package com.mymeds.objects;

import java.util.Date;

/**
 * Created by daniel on 25/03/2017.
 */

public class RlMedicamentoSubstancia {


    private Long idRlMedicamentoSubstancia;
    private Medicamento medicamento;
    private SubstanciaActiva substanciaActiva;
    private String createdBy;
    private String updatedBy;
    private Date creationDate;
    private Date updateDate;
    private Integer enabled;

    public RlMedicamentoSubstancia() {
    }

    public Long getIdRlMedicamentoSubstancia() {
        return idRlMedicamentoSubstancia;
    }

    public void setIdRlMedicamentoSubstancia(Long idRlMedicamentoSubstancia) {
        this.idRlMedicamentoSubstancia = idRlMedicamentoSubstancia;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public SubstanciaActiva getSubstanciaActiva() {
        return substanciaActiva;
    }

    public void setSubstanciaActiva(SubstanciaActiva substanciaActiva) {
        this.substanciaActiva = substanciaActiva;
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
}
