package br.com.syndic.controller;

import br.com.syndic.frame.Condominio;
import br.com.syndic.vo.CondominioVO;
import org.openswing.swing.table.client.GridController;
import java.util.*;
import org.openswing.swing.message.receive.java.*;
import java.sql.*;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.mdi.client.MDIFrame;

public class CondominioController extends GridController implements GridDataLocator {

    private Condominio grid = null;
    private Connection conn = null;

    public CondominioController(Connection conn) {
        this.conn = conn;
        grid = new Condominio(conn, this);
        MDIFrame.add(grid, true);
    }

    /**
     * Callback method invoked when the user has double clicked on the selected row of the grid.
     * @param rowNumber selected row index
     * @param persistentObject v.o. related to the selected row
     */
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        CondominioVO vo = (CondominioVO) persistentObject;
        new CondominioDetalheController(grid, vo.getCodCondominio().toString(), conn);
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
    public Response loadData(
            int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {
        PreparedStatement stmt = null;
        try {
            String sql = "select cod_cond ,nome_cond,endereco_cond,bairro_cond, " +
                    "cidade_cond,uf_cond,cep_cond,fone_cond,fax_cond,cnpj_cond,email_cond,sindico_cond," +
                    "instrucao1,instrucao2,instrucao3,instrucao4,mora_cond,multa_cond,rateio_cond,dia_vencimento_cond from condominio";

            Vector vals = new Vector();

            /*if (filteredColumns.size()>0) {
            FilterWhereClause[] filter = (FilterWhereClause[])filteredColumns.get("stringValue");
            sql += " and DEMO2.TEXT "+ filter[0].getOperator()+"?";
            vals.add(filter[0].getValue());
            if (filter[1]!=null) {
            sql += " and DEMO2.TEXT "+ filter[1].getOperator()+"?";
            vals.add(filter[1].getValue());
            }
            }
            if (currentSortedColumns.size()>0) {
            sql += " ORDER BY DEMO2.TEXT "+currentSortedVersusColumns.get(0);
            }*/

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < vals.size(); i++) {
                stmt.setObject(i + 1, vals.get(i));
            }

            ResultSet rset = stmt.executeQuery();

            ArrayList list = new ArrayList();
            CondominioVO vo = null;
            while (rset.next()) {
                System.out.println();
                vo = new CondominioVO();
                vo.setCodCondominio(rset.getInt(1));
                vo.setNomeCondominio(rset.getString(2));
                vo.setEnderecoCondominio(rset.getString(3));
                vo.setBairroCondominio(rset.getString(4));
                vo.setCidadeCondominio(rset.getString(5));
                vo.setUfCondominio(rset.getString(6));
                vo.setCepCondominio(rset.getString(7));
                vo.setFoneCondominio(rset.getString(8));
                vo.setFaxCondominio(rset.getString(9));
                vo.setCnpjCondominio(rset.getString(10));
                vo.setEmailCondominio(rset.getString(11));
                vo.setSindicoCondominio(rset.getString(12));
                vo.setInstrucao1(rset.getString(13));
                vo.setInstrucao2(rset.getString(14));
                vo.setInstrucao3(rset.getString(15));
                vo.setInstrucao4(rset.getString(16));
                vo.setMoraCondominio(rset.getDouble(17));
                vo.setMultaCondominio(rset.getDouble(18));
                vo.setRateioCondominio(rset.getDouble(19));
                vo.setDiaVencimento(rset.getString(20));
                list.add(vo);
            }
            return new VOListResponse(list, false, list.size());
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex1) {
            }
        }

    }

    /**
     * Method invoked when the user has clicked on delete button and the grid is in READONLY mode.
     * @param persistentObjects value objects to delete (related to the currently selected rows)
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from condominio where cod_cond=?");
            for (int i = 0; i < persistentObjects.size(); i++) {
                CondominioVO vo = (CondominioVO) persistentObjects.get(i);
                stmt.setString(1, vo.getCodCondominio().toString());
                stmt.execute();
            }
            return new VOResponse(new Boolean(true));
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
}