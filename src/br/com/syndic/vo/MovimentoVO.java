package br.com.syndic.vo;

import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class MovimentoVO extends ValueObjectImpl {

    private Integer codigo;
    private Integer codConta;
    private String descConta;
    private Integer codCondominio;
    private String nomeCondominio;
    private String mesAno;
    private Integer numeroCheque;
    private String descricao;
    private String documento;
    private Date dataLancamento;
    private Date dataCheque;
    private Double valorDocumento;

    public MovimentoVO() {
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codConta
     */
    public Integer getCodConta() {
        return codConta;
    }

    /**
     * @param codConta the codConta to set
     */
    public void setCodConta(Integer codConta) {
        this.codConta = codConta;
    }

    /**
     * @return the descConta
     */
    public String getDescConta() {
        return descConta;
    }

    /**
     * @param descConta the descConta to set
     */
    public void setDescConta(String descConta) {
        this.descConta = descConta;
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
     * @return the numeroCheque
     */
    public Integer getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * @param numeroCheque the numeroCheque to set
     */
    public void setNumeroCheque(Integer numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the dataLancamento
     */
    public Date getDataLancamento() {
        return dataLancamento;
    }

    /**
     * @param dataLancamento the dataLancamento to set
     */
    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * @return the dataCheque
     */
    public Date getDataCheque() {
        return dataCheque;
    }

    /**
     * @param dataCheque the dataCheque to set
     */
    public void setDataCheque(Date dataCheque) {
        this.dataCheque = dataCheque;
    }

    /**
     * @return the valorDocumento
     */
    public Double getValorDocumento() {
        return valorDocumento;
    }

    /**
     * @param valorDocumento the valorDocumento to set
     */
    public void setValorDocumento(Double valorDocumento) {
        this.valorDocumento = valorDocumento;
    }
    

}