/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia.entidades;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Samuel
 */
@Embeddable
public class ItemLocacaoId implements Serializable {
    
    private Integer locacaoId;
    private Integer exemplarCodigoInterno;
    
    @Override
    public int hashCode() {
        return Objects.hash(locacaoId, exemplarCodigoInterno);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemLocacaoId other = (ItemLocacaoId) obj;
        if (this.locacaoId != other.locacaoId) {
            return false;
        }
        return Objects.equals(this.locacaoId, other.locacaoId) &&
            Objects.equals(this.exemplarCodigoInterno, other.exemplarCodigoInterno);
    }
    
    
    public Integer getLocacaoId() {
        return locacaoId;
    }

    public void setLocacaoId(Integer locacaoId) {
        this.locacaoId = locacaoId;
    }

    public Integer getExemplarCodigoInterno() {
        return exemplarCodigoInterno;
    }

    public void setExemplarCodigoInterno(int exemplarCodigoInterno) {
        this.exemplarCodigoInterno = exemplarCodigoInterno;
    }
    
    
}
