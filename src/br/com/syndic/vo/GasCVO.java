/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syndic.vo;

import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class GasCVO extends ValueObjectImpl {

    private Integer codGas;
    private Integer codCondominio;
    private String nomeCondominio;
    private String mesAno;
    private Double vlrCompra;
    private Double fatorConversao;
    private Double totalConsumos;

    public GasCVO() {
    }

    /**
     * @return the codGas
     */
    public Integer getCodGas() {
        return codGas;
    }

    /**
     * @param codGas the codGas to set
     */
    public void setCodGas(Integer codGas) {
        this.codGas = codGas;
    }

    /**
     * @return the codCondominio
     */
    public Integer getCodCondominio() {
        return codCondominio;
    }

    /**
     * @param codCondominio the codCondominio to set
     */
    public void setCodCondominio(Integer codCondominio) {
        this.codCondominio = codCondominio;
    }

    /**
     * @return the nomeCondominio
     */
    public String getNomeCondominio() {
        return nomeCondominio;
    }

    /**
     * @param nomeCondominio the nomeCondominio to set
     */
    public void setNomeCondominio(String nomeCondominio) {
        this.nomeCondominio = nomeCondominio;
    }

    /**
     * @return the mesAno
     */
    public String getMesAno() {
        return mesAno;
    }

    /**
     * @param mesAno the mesAno to set
     */
    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    /**
     * @return the vlrCompra
     */
    public Double getVlrCompra() {
        return vlrCompra;
    }

    /**
     * @param vlrCompra the vlrCompra to set
     */
    public void setVlrCompra(Double vlrCompra) {
        this.vlrCompra = vlrCompra;
    }

    /**
     * @return the fatorConversao
     */
    public Double getFatorConversao() {
        return fatorConversao;
    }

    /**
     * @param fatorConversao the fatorConversao to set
     */
    public void setFatorConversao(Double fatorConversao) {
        this.fatorConversao = fatorConversao;
    }

    /**
     * @return the totalConsumos
     */
    public Double getTotalConsumos() {
        return totalConsumos;
    }

    /**
     * @param totalConsumos the totalConsumos to set
     */
    public void setTotalConsumos(Double totalConsumos) {
        this.totalConsumos = totalConsumos;
    }

    
}
