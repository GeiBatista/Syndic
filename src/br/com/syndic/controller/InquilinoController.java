package br.com.syndic.controller;

import br.com.syndic.frame.Inquilino;
import br.com.syndic.vo.InquilinoVO;
import org.openswing.swing.table.client.GridController;
import java.util.*;
import org.openswing.swing.message.receive.java.*;
import java.sql.*;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.send.java.FilterWhereClause;

public class InquilinoController extends GridController implements GridDataLocator {

    private Inquilino grid = null;
    private Connection conn = null;

    public InquilinoController(Connection conn) {
        this.conn = conn;
        grid = new Inquilino(conn, this);
        MDIFrame.add(grid, true);
    }

    /**
     * Callback method invoked when the user has double clicked on the selected row of the grid.
     * @param rowNumber selected row index
     * @param persistentObject v.o. related to the selected row
     */
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        InquilinoVO vo = (InquilinoVO) persistentObject;
        new InquilinoDetalheController(grid, vo.getCodInquilino(), conn);
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

            String sql = "select inquilino.cod_inq,inquilino.condominio_cod_cond,inquilino.apto_inq,inquilino.bloco_inq," +
                    "inquilino.quadra_inq,inquilino.nome_inq,inquilino.fone_inq,inquilino.celular_inq,inquilino.vlr_cota_normal_inq," +
                    "inquilino.fracao_ideal_inq,inquilino.cpf_inq,condominio.nome_cond" +
                    " from inquilino,condominio where inquilino.condominio_cod_cond=condominio.cod_cond";

            HashMap map = new HashMap();
            map.put("codInquilino", "inquilino.cod_inqu");
            map.put("codCondominio", "inquilino.condominio_cod_cond");
            map.put("apto", "inquilino.apto_inq");
            map.put("bloco", "inquilino.bloco_inq");
            map.put("quadra", "inquilino.quadra_inq");
            map.put("nomeInquilino", "inquilino.nome_inq");
            map.put("foneInquilino", "inquilino.fone_inq");
            map.put("celularInquilino", "inquilino.celular_inq");
            map.put("cotaNormal", "inquilino.vlr_cota_normal_inq");
            map.put("fracaoIdeal", "inquilino.fracao_ideal_inq");
            map.put("cpfInquilino", "inquilino.cpf_inq");


            Vector vals = new Vector();

            if (filteredColumns.size() > 0) {
                FilterWhereClause[] filtro = (FilterWhereClause[]) filteredColumns.get("nomeInquilino");
                sql += " where inquilino.nome_inq " + filtro[0].getOperator() + "?";
                vals.add(filtro[0].getValue());
            }

            if (currentSortedColumns.size() > 0) {
                sql += " order by " + map.get(currentSortedColumns.get(0)).toString() + " " + currentSortedVersusColumns.get(0);
            }

            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < vals.size(); i++) {
                stmt.setObject(i + 1, vals.get(i));
            }

            ResultSet rset = stmt.executeQuery();

            ArrayList list = new ArrayList();
            InquilinoVO vo = null;
            while (rset.next()) {
                vo = new InquilinoVO();
                vo.setCodInquilino(rset.getString(1));
                vo.setCodCondominio(rset.getInt(2));
                vo.setApto(rset.getString(3));
                vo.setBloco(rset.getString(4));
                vo.setQuadra(rset.getString(5));
                vo.setNomeInquilino(rset.getString(6));
                vo.setFoneInquilino(rset.getString(7));
                vo.setCelularInquilino(rset.getString(8));
                vo.setCotaNormal(rset.getDouble(9));
                vo.setFracaoIdeal(rset.getDouble(10));
                vo.setCpfInquilino(rset.getString(11));
                vo.setNomeCondominio(rset.getString(12));
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
            stmt = conn.prepareStatement("delete from inquilino where cod_inq=?");
            for (int i = 0; i < persistentObjects.size(); i++) {
                InquilinoVO vo = (InquilinoVO) persistentObjects.get(i);
                stmt.setString(1, vo.getCodInquilino());
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