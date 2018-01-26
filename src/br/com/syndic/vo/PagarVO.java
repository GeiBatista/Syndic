package br.com.syndic.vo;

import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class PagarVO extends ValueObjectImpl {

    private Integer codigo;
    private Integer codConta;
    private String descConta;
    private Integer codCondominio;
    private String nomeCondominio;
    private Integer codFornecedor;
    private String nomeFornecedor;
    private String notaFiscal;
    private Double vlrNota;
    private Double vlrJuro;
    private Double vlrMulta;
    private Double vlrPago;
    private Integer numeroCheque;
    private String chequeNominal;
    private Date dataEmissao;
    private Date dataEntrada;
    private Date dataVencimento;
    private Date dataLancamento;
    private Date dataPagamento;
    private Date dataCheque;
    private String mesAno;
    private String selecao;

    public PagarVO() {
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
     * @return the codFornecedor
     */
    public Integer getCodFornecedor() {
        return codFornecedor;
    }

    /**
     * @param codFornecedor the codFornecedor to set
     */
    public void setCodFornecedor(Integer codFornecedor) {
        this.codFornecedor = codFornecedor;
    }

    /**
     * @return the nomeFornecedor
     */
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    /**
     * @param nomeFornecedor the nomeFornecedor to set
     */
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    /**
     * @return the notaFiscal
     */
    public String getNotaFiscal() {
        return notaFiscal;
    }

    /**
     * @param notaFiscal the notaFiscal to set
     */
    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    /**
     * @return the vlrNota
     */
    public Double getVlrNota() {
        return vlrNota;
    }

    /**
     * @param vlrNota the vlrNota to set
     */
    public void setVlrNota(Double vlrNota) {
        this.vlrNota = vlrNota;
    }

    /**
     * @return the vlrJuro
     */
    public Double getVlrJuro() {
        return vlrJuro;
    }

    /**
     * @param vlrJuro the vlrJuro to set
     */
    public void setVlrJuro(Double vlrJuro) {
        this.vlrJuro = vlrJuro;
    }

    /**
     * @return the vlrMulta
     */
    public Double getVlrMulta() {
        return vlrMulta;
    }

    /**
     * @param vlrMulta the vlrMulta to set
     */
    public void setVlrMulta(Double vlrMulta) {
        this.vlrMulta = vlrMulta;
    }

    /**
     * @return the vlrPago
     */
    public Double getVlrPago() {
        return vlrPago;
    }

    /**
     * @param vlrPago the vlrPago to set
     */
    public void setVlrPago(Double vlrPago) {
        this.vlrPago = vlrPago;
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
     * @return the chequeNominal
     */
    public String getChequeNominal() {
        return chequeNominal;
    }

    /**
     * @param chequeNominal the chequeNominal to set
     */
    public void setChequeNominal(String chequeNominal) {
        this.chequeNominal = chequeNominal;
    }

    /**
     * @return the dataEmissao
     */
    public Date getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the dataEntrada
     */
    public Date getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the dataVencimento
     */
    public Date getDataVencimento() {
        return dataVencimento;
    }

    /**
     * @param dataVencimento the dataVencimento to set
     */
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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
     * @return the dataPagamento
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }

    /**
     * @param dataPagamento the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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
     * @return the selecao
     */
    public String getSelecao() {
        return selecao;
    }

    /**
     * @param selecao the selecao to set
     */
    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }
}
