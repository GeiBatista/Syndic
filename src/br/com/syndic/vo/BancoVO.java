package br.com.syndic.vo;

import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class BancoVO extends ValueObjectImpl {

    private Integer codBanco;
    private Integer codCompensacao;
    private String nomeBanco;
    private String siteBanco;

    public BancoVO() {
    }

    /**
     * @return the codBanco
     */
    public Integer getCodBanco() {
        return codBanco;
    }

    /**
     * @param codBanco the codBanco to set
     */
    public void setCodBanco(Integer codBanco) {
        this.codBanco = codBanco;
    }

    /**
     * @return the codCompensacao
     */
    public Integer getCodCompensacao() {
        return codCompensacao;
    }

    /**
     * @param codCompensacao the codCompensacao to set
     */
    public void setCodCompensacao(Integer codCompensacao) {
        this.codCompensacao = codCompensacao;
    }

    /**
     * @return the nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * @param nomeBanco the nomeBanco to set
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    /**
     * @return the siteBanco
     */
    public String getSiteBanco() {
        return siteBanco;
    }

    /**
     * @param siteBanco the siteBanco to set
     */
    public void setSiteBanco(String siteBanco) {
        this.siteBanco = siteBanco;
    }

    
}
