package com.mymeds.objects; 

/**
 * Created by daniel on 25/03/2017.
 */

import java.util.Date;

public class TablesInfo {


    private Long idTablesInfo;
    private Boolean medicamento;
    private Boolean tipoMedicamento;
    private Boolean substanciaActiva;
    private Boolean pais;
    private String createdBy;
    private String updatedBy;
    private Date creationDate;
    private Date updateDate;
    private Integer enabled;


    public TablesInfo() {
    }
  
    public Long getIdTablesInfo() {
		return idTablesInfo;
	}

	public void setIdTablesInfo(Long idTablesInfo) {
		this.idTablesInfo = idTablesInfo;
	}

	public Boolean getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Boolean medicamento) {
		this.medicamento = medicamento;
	}

	public Boolean getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(Boolean tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	public Boolean getSubstanciaActiva() {
		return substanciaActiva;
	}

	public void setSubstanciaActiva(Boolean substanciaActiva) {
		this.substanciaActiva = substanciaActiva;
	}

	public Boolean getPais() {
		return pais;
	}

	public void setPais(Boolean pais) {
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

	@Override
	public String toString() {
		return "TablesInfo [idTablesInfo=" + idTablesInfo + ", medicamento=" + medicamento + ", tipoMedicamento="
				+ tipoMedicamento + ", substanciaActiva=" + substanciaActiva + ", pais=" + pais + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", creationDate=" + creationDate + ", updateDate="
				+ updateDate + ", enabled=" + enabled + "]";
	}

  

}
