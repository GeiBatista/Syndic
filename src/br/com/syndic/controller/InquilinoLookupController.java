package br.com.syndic.controller;

import br.com.syndic.vo.CondominioVO;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.lookup.client.LookupDataLocator;
import org.openswing.swing.message.receive.java.*;
import java.sql.*;
import java.util.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

public class InquilinoLookupController extends LookupController {

    private Connection conn = null;
    public static VOListResponse condominioImp;

    public InquilinoLookupController(Connection conn) {
        this.conn = conn;
        this.setLookupDataLocator(new LookupDataLocator() {

            /**
             * Method called by lookup controller when validating code.
             * @param code code to validate
             * @return code validation response: VOListResponse if code validation has success, ErrorResponse otherwise
             */
            public Response validateCode(String code) {
                Statement stmt = null;
                try {
                    stmt = InquilinoLookupController.this.conn.createStatement();
                    ResultSet rset = stmt.executeQuery(
                            "select condominio.cod_cond,condominio.nome_cond,condominio.bairro_cond,condominio.cidade_cond,condominio.uf_cond,condominio.rateio_cond from condominio where cod_cond='" +
                            code + "'");
                    ArrayList list = new ArrayList();
                    while (rset.next()) {
                        CondominioVO vo = new CondominioVO();
                        vo.setCodCondominio(rset.getInt(1));
                        vo.setNomeCondominio(rset.getString(2));
                        vo.setBairroCondominio(rset.getString(3));
                        vo.setCidadeCondominio(rset.getString(4));
                        vo.setUfCondominio(rset.getString(5));
                        vo.setRateioCondominio(rset.getDouble(6));
                        list.add(vo);
                    }
                    if (list.size() > 0) {
                        condominioImp = new VOListResponse(list, false, list.size());
                        return condominioImp;
                    } else {
                        return new ErrorResponse("Código Inválido!");
                    }
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
             * Method called by lookup controller when user clicks on lookup button.
             * @param action fetching versus: PREVIOUS_BLOCK_ACTION, NEXT_BLOCK_ACTION or LAST_BLOCK_ACTION
             * @param startIndex current index row on grid to use to start fetching data
             * @param filteredColumns filtered columns
             * @param currentSortedColumns sorted columns
             * @param currentSortedVersusColumns ordering versus of sorted columns
             * @param valueObjectType type of value object associated to the lookup grid
             * @return list of value objects to fill in the lookup grid: VOListResponse if data fetching has success, ErrorResponse otherwise
             */
            public Response loadData(
                    int action,
                    int startIndex,
                    Map filteredColumns,
                    ArrayList currentSortedColumns,
                    ArrayList currentSortedVersusColumns,
                    Class valueObjectType) {
                Statement stmt = null;
                try {
                    stmt = InquilinoLookupController.this.conn.createStatement();
                    ResultSet rset = stmt.executeQuery(
                            "select condominio.cod_cond,condominio.nome_cond,condominio.bairro_cond,condominio.cidade_cond,condominio.uf_cond,condominio.rateio_cond from condominio");
                    ArrayList list = new ArrayList();
                    while (rset.next()) {
                        CondominioVO vo = new CondominioVO();

                        vo.setCodCondominio(rset.getInt(1));
                        vo.setNomeCondominio(rset.getString(2));
                        vo.setBairroCondominio(rset.getString(3));
                        vo.setCidadeCondominio(rset.getString(4));
                        vo.setUfCondominio(rset.getString(5));
                        vo.setRateioCondominio(rset.getDouble(6));

                        list.add(vo);
                    }
                    condominioImp = new VOListResponse(list, false, list.size());
                    return condominioImp;
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
             * Method called by the TreePanel to fill the tree.
             * @return a VOReponse containing a DefaultTreeModel object
             */
            public Response getTreeModel(JTree tree) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
        });

        this.setLookupValueObjectClassName("br.com.syndic.vo.CondominioVO");
        this.addLookup2ParentLink("codCondominio", "codCondominio");
        this.addLookup2ParentLink("nomeCondominio", "nomeCondominio");
        this.setAllColumnVisible(true);
    }
}