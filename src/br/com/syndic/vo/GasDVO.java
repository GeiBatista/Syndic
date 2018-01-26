/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syndic.vo;

import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class GasDVO extends ValueObjectImpl {

    private Integer codGas;
    private String codInquilino;
    private String nomeInquilino;
    private Double leituraAnterior;
    private Double leituraAtual;
    private Double consumoMes;
    private Double vlrPagamento;

    public GasDVO() {
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
     * @return the codInquilino
     */
    public String getCodInquilino() {
        return codInquilino;
    }

    /**
     * @param codInquilino the codInquilino to set
     */
    public void setCodInquilino(String codInquilino) {
        this.codInquilino = codInquilino;
    }

    /**
     * @return the leituraAnterior
     */
    public Double getLeituraAnterior() {
        return leituraAnterior;
    }

    /**
     * @param leituraAnterior the leituraAnterior to set
     */
    public void setLeituraAnterior(Double leituraAnterior) {
        this.leituraAnterior = leituraAnterior;
    }

    /**
     * @return the leituraAtual
     */
    public Double getLeituraAtual() {
        return leituraAtual;
    }

    /**
     * @param leituraAtual the leituraAtual to set
     */
    public void setLeituraAtual(Double leituraAtual) {
        this.leituraAtual = leituraAtual;
    }

    /**
     * @return the vlrPagamento
     */
    public Double getVlrPagamento() {
        return vlrPagamento;
    }

    /**
     * @param vlrPagamento the vlrPagamento to set
     */
    public void setVlrPagamento(Double vlrPagamento) {
        this.vlrPagamento = vlrPagamento;
    }

    /**
     * @return the nomeInquilino
     */
    public String getNomeInquilino() {
        return nomeInquilino;
    }

    /**
     * @param nomeInquilino the nomeInquilino to set
     */
    public void setNomeInquilino(String nomeInquilino) {
        this.nomeInquilino = nomeInquilino;
    }

    /**
     * @return the consumoMes
     */
    public Double getConsumoMes() {
        return consumoMes;
    }

    /**
     * @param consumoMes the consumoMes to set
     */
    public void setConsumoMes(Double consumoMes) {
        this.consumoMes = consumoMes;
    }
}