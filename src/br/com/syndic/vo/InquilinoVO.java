    package br.com.syndic.vo;

import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class InquilinoVO extends ValueObjectImpl {

    private String codInquilino;
    private Integer codCondominio;
    private String nomeCondominio;
    private String apto;
    private String bloco;
    private String quadra;
    private String nomeInquilino;
    private String foneInquilino;
    private String celularInquilino;
    private String cpfInquilino;
    private Double cotaNormal;
    private Double fracaoIdeal;


    public InquilinoVO() {
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
     * @return the apto
     */
    public String getApto() {
        return apto;
    }

    /**
     * @param apto the apto to set
     */
    public void setApto(String apto) {
        this.apto = apto;
    }

    /**
     * @return the bloco
     */
    public String getBloco() {
        return bloco;
    }

    /**
     * @param bloco the bloco to set
     */
    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    /**
     * @return the quadra
     */
    public String getQuadra() {
        return quadra;
    }

    /**
     * @param quadra the quadra to set
     */
    public void setQuadra(String quadra) {
        this.quadra = quadra;
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
     * @return the foneInquilino
     */
    public String getFoneInquilino() {
        return foneInquilino;
    }

    /**
     * @param foneInquilino the foneInquilino to set
     */
    public void setFoneInquilino(String foneInquilino) {
        this.foneInquilino = foneInquilino;
    }

    /**
     * @return the celularInquilino
     */
    public String getCelularInquilino() {
        return celularInquilino;
    }

    /**
     * @param celularInquilino the celularInquilino to set
     */
    public void setCelularInquilino(String celularInquilino) {
        this.celularInquilino = celularInquilino;
    }

    /**
     * @return the cpfInquilino
     */
    public String getCpfInquilino() {
        return cpfInquilino;
    }

    /**
     * @param cpfInquilino the cpfInquilino to set
     */
    public void setCpfInquilino(String cpfInquilino) {
        this.cpfInquilino = cpfInquilino;
    }

    /**
     * @return the cotaNormal
     */
    public Double getCotaNormal() {
        return cotaNormal;
    }

    /**
     * @param cotaNormal the cotaNormal to set
     */
    public void setCotaNormal(Double cotaNormal) {
        this.cotaNormal = cotaNormal;
    }

    /**
     * @return the fracaoIdeal
     */
    public Double getFracaoIdeal() {
        return fracaoIdeal;
    }

    /**
     * @param fracaoIdeal the fracaoIdeal to set
     */
    public void setFracaoIdeal(Double fracaoIdeal) {
        this.fracaoIdeal = fracaoIdeal;
    }

}