/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BancoDetalhe.java
 *
 * Created on 02/05/2009, 05:09:24
 */
package br.com.syndic.frame;

import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.SwingConstants;
import org.openswing.swing.client.DeleteButton;
import org.openswing.swing.client.EditButton;
import org.openswing.swing.client.SaveButton;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author Albert Eije
 */
public class BancoDetalhe extends InternalFrame implements ActionListener {

    private Connection conn = null;

    public BancoDetalhe(Connection conn, FormController dataController) {
        try {
            this.conn = conn;
            initComponents();
            form1.setFormController(dataController);
            setSize(590, 440);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Form getForm1() {
        return form1;
    }

    public SaveButton getSaveButton() {
        return saveButton1;
    }

    public DeleteButton getDeleteButton() {
        return deleteButton1;
    }

    public EditButton getEditButton() {
        return editButton1;
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
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        copyButton1 = new org.openswing.swing.client.CopyButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        textControl2 = new org.openswing.swing.client.TextControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();

        setTitle("Cadastro de Bancos (Inserindo/Alterando)");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(insertButton1);
        jPanel1.add(editButton1);
        jPanel1.add(copyButton1);
        jPanel1.add(deleteButton1);
        jPanel1.add(reloadButton1);

        saveButton1.addActionListener(this);
        jPanel1.add(saveButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("br.com.syndic.vo.BancoVO");
        form1.setCopyButton(copyButton1);
        form1.setDeleteButton(deleteButton1);
        form1.setEditButton(editButton1);
        form1.setInsertButton(insertButton1);
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        textControl1.setAttributeName("nomeBanco");
        textControl1.setCanCopy(false);
        textControl1.setEnabled(false);
        textControl1.setLinkLabel(labelControl2);
        textControl1.setRequired(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        form1.add(textControl1, gridBagConstraints);

        textControl2.setAttributeName("siteBanco");
        textControl2.setCanCopy(false);
        textControl2.setEnabled(false);
        textControl2.setLinkLabel(labelControl3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        form1.add(textControl2, gridBagConstraints);

        numericControl1.setAttributeName("codCompensacao");
        numericControl1.setCanCopy(false);
        numericControl1.setEnabled(false);
        numericControl1.setLinkLabel(labelControl1);
        numericControl1.setTextAlignment(SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        form1.add(numericControl1, gridBagConstraints);

        labelControl1.setLabel("Código Compensação:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        form1.add(labelControl1, gridBagConstraints);

        labelControl2.setLabel("Nome do Banco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        form1.add(labelControl2, gridBagConstraints);

        labelControl3.setLabel("Site do Banco:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        form1.add(labelControl3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == saveButton1) {
            BancoDetalhe.this.saveButton1ActionPerformed(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CopyButton copyButton1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables
}
