/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Banco.java
 *
 * Created on 02/05/2009, 04:32:48
 */

package br.com.syndic.frame;

import br.com.syndic.controller.RateioGasController;
import br.com.syndic.controller.RateioGasDetalheController;
import br.com.syndic.controller.RateioGasLookupController;
import br.com.syndic.vo.GasCVO;
import br.com.syndic.vo.GasDVO;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author Albert Eije
 */
public class Pagamentos extends InternalFrame implements ActionListener {

    private Connection conn = null;
    private RateioGasController cabControle;
    private RateioGasDetalheController detControle;
    private boolean movimentoNovo;

    /** Creates new form GridFrame2 */
    public Pagamentos(Connection conn) {
        this.conn = conn;
        try {
            initComponents();
            setSize(750, 300);

            //this.cabControle = cabControle;
            form1.setFormController(cabControle);

//            detControle = new RateioGasDetalheController(this, conn);
            gridControl1.setController(detControle);
            gridControl1.setGridDataLocator(detControle);

            LookupController lookupController = new RateioGasLookupController(conn);
            txCodCondominio.setLookupController(lookupController);

            MaskFormatter mascaraMesAno = new MaskFormatter("##/####");
            mascaraMesAno.setValidCharacters("0123456789");
            txMesAno.setFormatter(mascaraMesAno);

            btInicia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iniciar.png")));
            btInicia.setPreferredSize(new Dimension(65, 41));
            btInicia.setToolTipText("Clique para iniciar o movimento");

            btConsultaMovimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pergunta.png")));
            btConsultaMovimento.setPreferredSize(new Dimension(65, 41));
            btConsultaMovimento.setToolTipText("Clique para verificar se já existe movimento");

            btImportaInquilinos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/importainq.png")));
            btImportaInquilinos.setPreferredSize(new Dimension(65, 41));
            btImportaInquilinos.setToolTipText("Clique para importar os inquilinos");

            btCalculaRateio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calculadora.png")));
            btCalculaRateio.setPreferredSize(new Dimension(65, 41));
            btCalculaRateio.setToolTipText("Clique para calcular o rateio");

            btCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancelar.png")));
            btCancela.setPreferredSize(new Dimension(65, 41));
            btCancela.setToolTipText("Clique para cancelar o movimento");

            btSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/confirmar.png")));
            btSalva.setPreferredSize(new Dimension(65, 41));
            btSalva.setToolTipText("Clique para confirmar o movimento");

            btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fechar.png")));
            btSair.setPreferredSize(new Dimension(65, 41));
            btSair.setToolTipText("Clique para sair da janela atual");

            desabilita();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadData() {
        getGridControl1().reloadData();
    }

    private void calculaRateio() {
        //mestre - Gas Cabecalho
        GasCVO gasC = (GasCVO) form1.getVOModel().getValueObject();

        //Detalhe Gas Detalhe
        GasDVO gasD = new GasDVO();
        Double totalConsumos = new Double(0);
        Double fatorConversao = new Double(0);

        //calcula o valor do consumo de cada inquilino
        //calcula o total geral dos consumos
        for (int i = 0; i < getGridControl1().getVOListTableModel().getRowCount(); i++) {
            gasD = (GasDVO) getGridControl1().getVOListTableModel().getObjectForRow(i);
            if (gasD.getLeituraAtual() != null) {
                gasD.setConsumoMes(gasD.getLeituraAtual() - gasD.getLeituraAnterior());
            }
            totalConsumos = totalConsumos + gasD.getConsumoMes();
        }

        //calcula o fator de conversão
        fatorConversao = gasC.getVlrCompra() / totalConsumos;

        //calcula o valor que cada inquilino deve pagar
        for (int i = 0; i < getGridControl1().getVOListTableModel().getRowCount(); i++) {
            gasD = (GasDVO) getGridControl1().getVOListTableModel().getObjectForRow(i);
            gasD.setVlrPagamento(gasD.getConsumoMes() * fatorConversao);
        }

        //atualiza edits FatorConversao e TotalConsumos
        gasC.setFatorConversao(fatorConversao);
        gasC.setTotalConsumos(totalConsumos);

        //atualiza controles graficos - repaint
        getGridControl1().repaint();
        form1.pull();
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        txCodCondominio = new org.openswing.swing.client.CodLookupControl();
        txNomeCondominio = new org.openswing.swing.client.TextControl();
        txMesAno = new org.openswing.swing.client.FormattedTextControl();
        jPanel2 = new javax.swing.JPanel();
        btInicia = new org.openswing.swing.client.InsertButton();
        btConsultaMovimento = new org.openswing.swing.client.GenericButton();
        btImportaInquilinos = new org.openswing.swing.client.GenericButton();
        btCalculaRateio = new org.openswing.swing.client.GenericButton();
        btCancela = new org.openswing.swing.client.ReloadButton();
        btSalva = new org.openswing.swing.client.SaveButton();
        btSair = new org.openswing.swing.client.GenericButton();
        gridControl1 = new org.openswing.swing.client.GridControl();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn2 = new org.openswing.swing.table.columns.client.IntegerColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn1 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn2 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn3 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn4 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();

        setTitle("Pagamentos");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        form1.setBackground(new java.awt.Color(0, 204, 204));
        form1.setVOClassName("br.com.syndic.vo.PagarVO");
        form1.setInsertButton(btInicia);
        form1.setNextFocusableComponent(txCodCondominio);
        form1.setReloadButton(btCancela);
        form1.setSaveButton(btSalva);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl2.setLabel("Mês/Ano:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        form1.add(labelControl2, gridBagConstraints);

        labelControl1.setLabel("Condomínio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        form1.add(labelControl1, gridBagConstraints);

        txCodCondominio.setAttributeName("codCondominio");
        txCodCondominio.setAutoCompletitionWaitTime(2L);
        txCodCondominio.setLinkLabel(labelControl1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        form1.add(txCodCondominio, gridBagConstraints);

        txNomeCondominio.setAttributeName("nomeCondominio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        form1.add(txNomeCondominio, gridBagConstraints);

        txMesAno.setAttributeName("mesAno");
        txMesAno.setLinkLabel(labelControl2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        form1.add(txMesAno, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(form1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btInicia.addActionListener(this);
        jPanel2.add(btInicia);

        btConsultaMovimento.addActionListener(this);
        jPanel2.add(btConsultaMovimento);

        btImportaInquilinos.addActionListener(this);
        jPanel2.add(btImportaInquilinos);

        btCalculaRateio.addActionListener(this);
        jPanel2.add(btCalculaRateio);

        btCancela.addActionListener(this);
        jPanel2.add(btCancela);

        btSalva.addActionListener(this);
        jPanel2.add(btSalva);

        btSair.addActionListener(this);
        jPanel2.add(btSair);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        gridControl1.setAutoLoadData(false);
        gridControl1.setFunctionId("banco");
        gridControl1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gridControl1.setValueObjectClassName("br.com.syndic.vo.PagarVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        integerColumn1.setColumnName("codConta");
        gridControl1.getColumnContainer().add(integerColumn1);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("descConta");
        textColumn1.setColumnSortable(true);
        textColumn1.setHeaderColumnName("Conta");
        gridControl1.getColumnContainer().add(textColumn1);

        integerColumn2.setColumnName("codFornecedor");
        gridControl1.getColumnContainer().add(integerColumn2);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("nomeFornecedor");
        textColumn2.setColumnSortable(true);
        textColumn2.setHeaderColumnName("Nome Fornecedor");
        textColumn2.setPreferredWidth(250);
        gridControl1.getColumnContainer().add(textColumn2);

        decimalColumn1.setColumnName("vlrNota");
        decimalColumn1.setDecimals(2);
        decimalColumn1.setHeaderColumnName("Cota Normal");
        gridControl1.getColumnContainer().add(decimalColumn1);

        decimalColumn2.setColumnName("vlrJuro");
        decimalColumn2.setDecimals(2);
        decimalColumn2.setEditableOnEdit(true);
        decimalColumn2.setEditableOnInsert(true);
        decimalColumn2.setHeaderColumnName("Valor Gás");
        gridControl1.getColumnContainer().add(decimalColumn2);

        decimalColumn3.setColumnName("vlrMulta");
        decimalColumn3.setDecimals(2);
        decimalColumn3.setHeaderColumnName("Cota Extra");
        gridControl1.getColumnContainer().add(decimalColumn3);

        decimalColumn4.setColumnName("numeroCheque");
        decimalColumn4.setDecimals(2);
        decimalColumn4.setHeaderColumnName("Valor a Pagar");
        gridControl1.getColumnContainer().add(decimalColumn4);

        dateColumn1.setColumnName("dataEmissao");
        gridControl1.getColumnContainer().add(dateColumn1);

        textColumn3.setColumnName("selecao");
        gridControl1.getColumnContainer().add(textColumn3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(gridControl1, gridBagConstraints);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btInicia) {
            Pagamentos.this.btIniciaActionPerformed(evt);
        }
        else if (evt.getSource() == btConsultaMovimento) {
            Pagamentos.this.btConsultaMovimentoActionPerformed(evt);
        }
        else if (evt.getSource() == btImportaInquilinos) {
            Pagamentos.this.btImportaInquilinosActionPerformed(evt);
        }
        else if (evt.getSource() == btCalculaRateio) {
            Pagamentos.this.btCalculaRateioActionPerformed(evt);
        }
        else if (evt.getSource() == btCancela) {
            Pagamentos.this.btCancelaActionPerformed(evt);
        }
        else if (evt.getSource() == btSalva) {
            Pagamentos.this.btSalvaActionPerformed(evt);
        }
        else if (evt.getSource() == btSair) {
            Pagamentos.this.btSairActionPerformed(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btIniciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciaActionPerformed
        btConsultaMovimento.setEnabled(true);
}//GEN-LAST:event_btIniciaActionPerformed

    private void btCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelaActionPerformed
        gridControl1.reloadData();
    }//GEN-LAST:event_btCancelaActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btConsultaMovimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultaMovimentoActionPerformed
        GasCVO gasC = (GasCVO) form1.getVOModel().getValueObject();
        form1.push();
        if ((gasC.getCodCondominio() == null) || (gasC.getMesAno() == null)) {
            JOptionPane.showMessageDialog(null, "Selecione primeiro um condomínio e informe o Mês/Ano");
            txCodCondominio.requestFocus();
        } else {
            try {
                movimentoNovo = cabControle.consultaMovimento();
            } catch (Exception ex) {
                Logger.getLogger(Pagamentos.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (movimentoNovo == true) {
                form1.save();
                form1.reload();
                btImportaInquilinos.setEnabled(true);
                btConsultaMovimento.setEnabled(false);
            } else {
                form1.setMode(Consts.READONLY);
                form1.pull();
                form1.reload();
                gridControl1.reloadData();
            }
        }
    }//GEN-LAST:event_btConsultaMovimentoActionPerformed

    private void btImportaInquilinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportaInquilinosActionPerformed
        if (movimentoNovo == true) {
            detControle.carregaInquilinoNovo();
        }
        gridControl1.setMode(Consts.EDIT);
        form1.setMode(Consts.EDIT);
        btImportaInquilinos.setEnabled(false);
        btCalculaRateio.setEnabled(true);
    }//GEN-LAST:event_btImportaInquilinosActionPerformed

    private void btCalculaRateioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalculaRateioActionPerformed
        calculaRateio();
    }//GEN-LAST:event_btCalculaRateioActionPerformed

    private void btSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvaActionPerformed
        gridControl1.save();
    }//GEN-LAST:event_btSalvaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.GenericButton btCalculaRateio;
    private org.openswing.swing.client.ReloadButton btCancela;
    private org.openswing.swing.client.GenericButton btConsultaMovimento;
    private org.openswing.swing.client.GenericButton btImportaInquilinos;
    private org.openswing.swing.client.InsertButton btInicia;
    private org.openswing.swing.client.GenericButton btSair;
    private org.openswing.swing.client.SaveButton btSalva;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn2;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn3;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn4;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.client.CodLookupControl txCodCondominio;
    private org.openswing.swing.client.FormattedTextControl txMesAno;
    private org.openswing.swing.client.TextControl txNomeCondominio;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the txCodCondominio
     */
    public org.openswing.swing.client.CodLookupControl getTxCodCondominio() {
        return txCodCondominio;
    }

    /**
     * @return the txMesAno
     */
    public org.openswing.swing.client.FormattedTextControl getTxMesAno() {
        return txMesAno;
    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
    }

    /**
     * @return the gridControl1
     */
    public org.openswing.swing.client.GridControl getGridControl1() {
        return gridControl1;
    }

    private void desabilita() {
        btImportaInquilinos.setEnabled(false);
        btCalculaRateio.setEnabled(false);
        btConsultaMovimento.setEnabled(false);
        /*btCancela.setEnabled(false);
        btSalva.setEnabled(false);
        btInicia.setEnabled(true);*/
        btSair.setEnabled(true);
        form1.setMode(Consts.READONLY);
    }

}
