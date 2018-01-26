package br.com.syndic.vo;

import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

public class ReceberVO extends ValueObjectImpl {

    private Integer codigo;
    private Integer codCondominio;
    private String nomeCondominio;
    private String codInquilino;
    private String nomeInquilino;
    private String mesAno;
    private Date vencimento;
    private Double cotaNormal;
    private Double cotaExtra;
    private Double mora;
    private Double multa;
    private Double gas;
    private Double receber;
    private Double taxaDesconto;
    private Double vlrDesconto;
    private Double recebido;
    private String houveCotaExtra;
    private String motivoCotaExtra;
    private String tipoRecebimento;
    private Date dataRecebimento;

    public ReceberVO() {
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
     * @return the vencimento
     */
    public Date getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
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
     * @return the cotaExtra
     */
    public Double getCotaExtra() {
        return cotaExtra;
    }

    /**
     * @param cotaExtra the cotaExtra to set
     */
    public void setCotaExtra(Double cotaExtra) {
        this.cotaExtra = cotaExtra;
    }

    /**
     * @return the mora
     */
    public Double getMora() {
        return mora;
    }

    /**
     * @param mora the mora to set
     */
    public void setMora(Double mora) {
        this.mora = mora;
    }

    /**
     * @return the multa
     */
    public Double getMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }

    /**
     * @return the gas
     */
    public Double getGas() {
        return gas;
    }

    /**
     * @param gas the gas to set
     */
    public void setGas(Double gas) {
        this.gas = gas;
    }

    /**
     * @return the receber
     */
    public Double getReceber() {
        return receber;
    }

    /**
     * @param receber the receber to set
     */
    public void setReceber(Double receber) {
        this.receber = receber;
    }

    /**
     * @return the taxaDesconto
     */
    public Double getTaxaDesconto() {
        return taxaDesconto;
    }

    /**
     * @param taxaDesconto the taxaDesconto to set
     */
    public void setTaxaDesconto(Double taxaDesconto) {
        this.taxaDesconto = taxaDesconto;
    }

    /**
     * @return the vlrDesconto
     */
    public Double getVlrDesconto() {
        return vlrDesconto;
    }

    /**
     * @param vlrDesconto the vlrDesconto to set
     */
    public void setVlrDesconto(Double vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    /**
     * @return the recebido
     */
    public Double getRecebido() {
        return recebido;
    }

    /**
     * @param recebido the recebido to set
     */
    public void setRecebido(Double recebido) {
        this.recebido = recebido;
    }

    /**
     * @return the houveCotaExtra
     */
    public String getHouveCotaExtra() {
        return houveCotaExtra;
    }

    /**
     * @param houveCotaExtra the houveCotaExtra to set
     */
    public void setHouveCotaExtra(String houveCotaExtra) {
        this.houveCotaExtra = houveCotaExtra;
    }

    /**
     * @return the motivoCotaExtra
     */
    public String getMotivoCotaExtra() {
        return motivoCotaExtra;
    }

    /**
     * @param motivoCotaExtra the motivoCotaExtra to set
     */
    public void setMotivoCotaExtra(String motivoCotaExtra) {
        this.motivoCotaExtra = motivoCotaExtra;
    }

    /**
     * @return the tipoRecebimento
     */
    public String getTipoRecebimento() {
        return tipoRecebimento;
    }

    /**
     * @param tipoRecebimento the tipoRecebimento to set
     */
    public void setTipoRecebimento(String tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
    }

    /**
     * @return the dataRecebimento
     */
    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    /**
     * @param dataRecebimento the dataRecebimento to set
     */
    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }
}