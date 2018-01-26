/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.syndic.controller;

import br.com.syndic.frame.RateioGas;
import br.com.syndic.vo.GasCVO;
import br.com.syndic.vo.GasDVO;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author Albert Eije
 */
public class RateioGasDetalheController extends GridController implements GridDataLocator {

    private Connection conn = null;
    private RateioGas gas = null;

    public RateioGasDetalheController(RateioGas gas, Connection conn) {
        this.conn = conn;
        this.gas = gas;
    }

    /**
     * Callback method invoked to load data on the grid.
     * @param action fetching versus: PREVIOUS_BLOCK_ACTION, NEXT_BLOCK_ACTION or LAST_BLOCK_ACTION
     * @param startPos start position of data fetching in result set
     * @param filteredColumns filtered columns
     * @param currentSortedColumns sorted columns
     * @param currentSortedVersusColumns ordering versus of sorted columns
     * @param valueObjectType v.o. type
     * @param otherGridParams other grid parameters
     * @return response from the server: an object of type VOListResponse if data loading was successfully completed, or an ErrorResponse onject if some error occours
     */
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {

        try {
            String sql =
                    "select gas_detalhe.gas_cabecalho_cod_gas_cab,gas_detalhe.inquilino_cod_inq, " +
                    "gas_detalhe.leitura_anterior, gas_detalhe.leitura_atual, " +
                    "gas_detalhe.consumo_mes, gas_detalhe.vlr_pagamento," +
                    "inquilino.nome_inq " +
                    "from gas_detalhe, inquilino " +
                    "where gas_detalhe.inquilino_cod_inq = inquilino.cod_inq and " +
                    "gas_detalhe.gas_cabecalho_cod_gas_cab = ?";

            // mapping between attributes and database fields...
            Map attribute2dbField = new HashMap();
            attribute2dbField.put("codGas", "gas_detalhe.gas_cabecalho_cod_gas_cab");
            attribute2dbField.put("codInquilino", "gas_detalhe.inquilino_cod_inq");
            attribute2dbField.put("nomeInquilino", "inquilino.nome_inq");
            attribute2dbField.put("leituraAnterior", "gas_detalhe.leitura_anterior");
            attribute2dbField.put("leituraAtual", "gas_detalhe.leitura_atual");
            attribute2dbField.put("consumoMes", "gas_detalhe.consumo_mes");
            attribute2dbField.put("vlrPagamento", "gas_detalhe.vlr_pagamento");

            GasCVO gasC = (GasCVO) gas.getForm1().getVOModel().getValueObject();
            ArrayList vars = new ArrayList();
            vars.add(gasC.getCodGas());

            Response res = QueryUtil.getQuery(
                    conn,
                    sql,
                    vars, // list of values linked to "?" parameters in sql
                    attribute2dbField,
                    GasDVO.class, // v.o. to dinamically create for each row...
                    "Y",
                    "N",
                    new GridParams(
                    action,
                    startIndex,
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    new HashMap() // other params...
                    ),
                    true // log query...
                    );

            ArrayList list = (ArrayList) ((VOListResponse) res).getRows();
            if (list.size() > 0) {
                return res;
            } else {
                carregaInquilinoNovo();
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        }
    }

    /**
     * Method called by the Form panel to insert new data.
     * @param newValueObject value object to save
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        // mapping between attributes and database fields...
        Map attribute2dbField = new HashMap();
        attribute2dbField.put("codGas", "gas_detalhe.gas_cabecalho_cod_gas_cab");
        attribute2dbField.put("codInquilino", "gas_detalhe.inquilino_cod_inq");
        attribute2dbField.put("leituraAnterior", "gas_detalhe.leitura_anterior");
        attribute2dbField.put("leituraAtual", "gas_detalhe.leitura_atual");
        attribute2dbField.put("consumoMes", "gas_detalhe.consumo_mes");
        attribute2dbField.put("vlrPagamento", "gas_detalhe.vlr_pagamento");

        Response res = QueryUtil.insertTable(conn, newPersistentObject, "gas_detalhe", attribute2dbField, "Y", "N", true);
        return res;
    }

    /**
     * Method invoked when the user has clicked on save button and the grid is in EDIT mode.
     * @param rowNumbers row indexes related to the changed rows
     * @param oldPersistentObjects old value objects, previous the changes
     * @param persistentObjects value objects relatied to the changed rows
     * @return an ErrorResponse value object in case of errors, VOListResponse if the operation is successfully completed
     */
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Map attribute2dbField = new HashMap();
        attribute2dbField.put("codGas", "gas_detalhe.gas_cabecalho_cod_gas_cab");
        attribute2dbField.put("codInquilino", "gas_detalhe.inquilino_cod_inq");
        attribute2dbField.put("leituraAnterior", "gas_detalhe.leitura_anterior");
        attribute2dbField.put("leituraAtual", "gas_detalhe.leitura_atual");
        attribute2dbField.put("consumoMes", "gas_detalhe.consumo_mes");
        attribute2dbField.put("vlrPagamento", "gas_detalhe.vlr_pagamento");

        HashSet pk = new HashSet();
        pk.add("codGas");
        pk.add("codInquilino");

        Response res = null;
        GasDVO oldVO = null;
        GasDVO newVO = null;

        for (int i = 0; i < persistentObjects.size(); i++) {
            oldVO = (GasDVO) oldPersistentObjects.get(i);
            newVO = (GasDVO) persistentObjects.get(i);
            res = QueryUtil.updateTable(conn, pk, oldVO, newVO, "gas_detalhe", attribute2dbField, "Y", "N", true);
        }
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    /**
     * Method called by the Form panel to delete existing data.
     * @param persistentObject value object to delete
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response deleteRecord(ValueObject persistentObject) throws Exception {
        PreparedStatement stmt = null;
        try {
            GasDVO vo = (GasDVO) persistentObject;

            // delete from gas detalhe
            stmt = conn.prepareStatement("delete from gas_detalhe where gas_cabecalho_cod_gas_cab=? and inquilino_cod_inq=?");
            stmt.setInt(1, vo.getCodGas());
            stmt.setString(2, vo.getCodInquilino());
            stmt.execute();
            stmt.close();

            return new VOResponse(vo);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                stmt.close();
                conn.commit();
            } catch (SQLException ex1) {
            }
        }
    }

    public void carregaInquilinoNovo() {

        PreparedStatement stmt = null;
        ResultSet rset = null;
        ResultSet rset2 = null;
        try {
            GasCVO gasC = (GasCVO) gas.getForm1().getVOModel().getValueObject();
            GasDVO gasD = null;

            String sql = "select inquilino.cod_inq,inquilino.nome_inq, " +
                    "inquilino.condominio_cod_cond" +
                    " from inquilino where inquilino.condominio_cod_cond=" + gasC.getCodCondominio();
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();

            if (!rset.next()) {
                JOptionPane.showMessageDialog(null, "Condomínio sem inquilinos cadastrados");
            } else {

                rset.beforeFirst();
                while (rset.next()) {

                    gasD = new GasDVO();
                    gasD.setCodGas(gasC.getCodGas());
                    gasD.setCodInquilino(rset.getString(1));
                    gasD.setNomeInquilino(rset.getString(2));


                    //tenta pegar a leitura atual do mes anterior e torna-la leitura anterior no mes atual
                    sql = "select gd.leitura_atual " +
                            "from gas_detalhe gd join gas_cabecalho gc " +
                            "on gc.cod_gas_cab = gd.gas_cabecalho_cod_gas_cab " +
                            " where gd.inquilino_cod_inq = '" + gasD.getCodInquilino() + "' and " +
                            " gc.mes_ano_gas_cab = '" + periodoAnterior(gasC.getMesAno()) + "'";
                    stmt = conn.prepareStatement(sql);
                    rset2 = stmt.executeQuery();

                    if (rset2.next()) {
                        gasD.setLeituraAnterior(rset2.getDouble(1));
                    } else {
                        gasD.setLeituraAnterior(0.0);
                    }

                    gasD.setConsumoMes(0.0);
                    gasD.setLeituraAtual(0.0);
                    gasD.setVlrPagamento(0.0);
                    try {
                        insertRecord(gasD);
                    } catch (Exception ex) {
                        Logger.getLogger(RateioGasDetalheController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gas.getGridControl1().reloadData();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex1) {
            }
        }
    }

    public String periodoAnterior(String periodo) {
        String periodoMes;
        String periodoAno;
        if (periodo.substring(0, 2).equals("01")) {
            periodoMes = "12";
            periodoAno = String.valueOf(Integer.valueOf(periodo.substring(3)) - 1);
        } else {
            periodoMes = String.valueOf(Integer.valueOf(periodo.substring(0, 2)) - 1);
            if (Integer.parseInt(periodoMes) < 10) {
                periodoMes = "0" + periodoMes;
            }
            periodoAno = periodo.substring(3);
        }
        return periodoMes + "/" + periodoAno;
    }

}
