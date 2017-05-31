package com.mymeds.objects;

import java.util.Date;

/**
 * Created by daniel on 25/03/2017.
 */

public class TipoUtilizador {

    private Long idTipoUtilizador;
    private String codigo;
    private String descricao;
    private String createdBy;
    private String updatedBy;
    private Date creationDate;
    private Date updateDate;
    private Integer enabled;

    public TipoUtilizador() {
    }

    public Long getIdTipoUtilizador() {
        return idTipoUtilizador;
    }

    public void setIdTipoUtilizador(Long idTipoUtilizador) {
        this.idTipoUtilizador = idTipoUtilizador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
