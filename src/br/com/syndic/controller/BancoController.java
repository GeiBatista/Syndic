package br.com.syndic.controller;

import br.com.syndic.frame.Banco;
import br.com.syndic.vo.BancoVO;
import org.openswing.swing.table.client.GridController;
import java.util.*;
import org.openswing.swing.message.receive.java.*;
import java.sql.*;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.send.java.FilterWhereClause;

public class BancoController extends GridController implements GridDataLocator {

    private Banco grid = null;
    private Connection conn = null;

    public BancoController(Connection conn) {
        this.conn = conn;
        grid = new Banco(conn, this);
        MDIFrame.add(grid, true);
    }

    /**
     * Callback method invoked when the user has double clicked on the selected row of the grid.
     * @param rowNumber selected row index
     * @param persistentObject v.o. related to the selected row
     */
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        BancoVO vo = (BancoVO) persistentObject;
        new BancoDetalheController(grid, vo.getCodBanco().toString(), conn);
    }

    /**
     * Callback method invoked to load data on the grid.
     * @param action fetching versus: PREVIOUS_BLOCK_ACTION, NEXT_BLOCK_ACTION or LAST_BLOCK_ACTION
     * @param startIndex
     * @param startPos start position of data fetching in result set
     * @param filteredColumns filtered columns
     * @param currentSortedColumns sorted columns
     * @param currentSortedVersusColumns ordering versus of sorted columns
     * @param valueObjectType v.o. type
     * @param otherGridParams other grid parameters
     * @return response from the server: an object of type VOListResponse if data loading was successfully completed, or an ErrorResponse onject if some error occours
     */
    @Override
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
            String sql = "select banco.cod_banco,banco.cod_compensacao,banco.nome_banco,banco.site_banco from banco";

            Vector vals = new Vector();

            Map mapa = new HashMap();
            mapa.put("codBanco", "banco.cod_banco");
            mapa.put("codCompensacao", "banco.cod_compensacao");
            mapa.put("nomeBanco", "banco.nome_banco");
            mapa.put("siteBanco", "banco.site_banco");

            if (filteredColumns.size() > 0) {
                FilterWhereClause[] filtro = (FilterWhereClause[]) filteredColumns.get("nomeBanco");
                sql += " where banco.nome_banco " + filtro[0].getOperator() + "?";
                vals.add(filtro[0].getValue());
            }

            if (currentSortedColumns.size() > 0) {
                sql += " order by " + mapa.get(currentSortedColumns.get(0).toString()) + " " + currentSortedVersusColumns.get(0);
            }

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < vals.size(); i++) {
                stmt.setObject(i + 1, vals.get(i));
            }

            ResultSet rset = stmt.executeQuery();

            ArrayList list = new ArrayList();
            BancoVO vo = null;
            while (rset.next()) {
                System.out.println();
                vo = new BancoVO();
                vo.setCodBanco(rset.getInt(1));
                vo.setCodCompensacao(rset.getInt(2));
                vo.setNomeBanco(rset.getString(3));
                vo.setSiteBanco(rset.getString(4));
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
            stmt = conn.prepareStatement("delete from banco where cod_banco=?");
            for (int i = 0; i < persistentObjects.size(); i++) {
                BancoVO vo = (BancoVO) persistentObjects.get(i);
                stmt.setString(1, vo.getCodBanco().toString());
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