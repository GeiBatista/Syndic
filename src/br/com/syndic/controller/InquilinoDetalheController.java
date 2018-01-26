package br.com.syndic.controller;

import br.com.syndic.frame.Inquilino;
import br.com.syndic.frame.InquilinoDetalhe;
import br.com.syndic.vo.InquilinoVO;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.util.java.Consts;
import java.sql.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.mdi.client.MDIFrame;

public class InquilinoDetalheController extends FormController {

    private InquilinoDetalhe frame = null;
    private Connection conn = null;
    private String pk = null;
    private Inquilino inquilinoFrame = null;

    public InquilinoDetalheController(Inquilino inquilinoFrame, String pk, Connection conn) {
        this.inquilinoFrame = inquilinoFrame;
        this.pk = pk;
        this.conn = conn;
        frame = new InquilinoDetalhe(conn, this);
        MDIFrame.add(frame);

        if (pk != null) {
            frame.getForm1().setMode(Consts.READONLY);
            frame.getForm1().reload();
        } else {
            frame.getForm1().setMode(Consts.INSERT);
        }

    }

    /**
     * This method must be overridden by the subclass to retrieve data and return the valorized value object.
     * @param valueObjectClass value object class
     * @return a VOResponse object if data loading is successfully completed, or an ErrorResponse object if an error occours
     */
    public Response loadData(Class valueObjectClass) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select inquilino.cod_inq," +
                    "inquilino.condominio_cod_cond," +
                    "inquilino.apto_inq," +
                    "inquilino.bloco_inq," +
                    "inquilino.quadra_inq," +
                    "inquilino.nome_inq," +
                    "inquilino.fone_inq," +
                    "inquilino.celular_inq," +
                    "inquilino.vlr_cota_normal_inq," +
                    "inquilino.fracao_ideal_inq," +
                    "inquilino.cpf_inq," +
                    "condominio.nome_cond" +
                    " from inquilino,condominio where cod_inq='" + pk + "' and inquilino.condominio_cod_cond=condominio.cod_cond");

            if (rset.next()) {
                InquilinoVO vo = new InquilinoVO();

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

                return new VOResponse(vo);
            } else {
                return new ErrorResponse("Nenhum registro encontrado.");
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
     * Method called by the Form panel to insert new data.
     * @param newValueObject value object to save
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into inquilino(condominio_cod_cond,apto_inq,bloco_inq,quadra_inq,nome_inq,fone_inq,celular_inq,vlr_cota_normal_inq,fracao_ideal_inq,cpf_inq,cod_inq) " +
                    " values(?,?,?,?,?,?,?,?,?,?,?)");
            InquilinoVO vo = (InquilinoVO) newPersistentObject;

            stmt.setInt(1,vo.getCodCondominio());
            stmt.setString(2,vo.getApto());
            stmt.setString(3,vo.getBloco());
            stmt.setString(4,vo.getQuadra());
            stmt.setString(5,vo.getNomeInquilino());
            stmt.setString(6,vo.getFoneInquilino());
            stmt.setString(7,vo.getCelularInquilino());
            stmt.setDouble(8,vo.getCotaNormal());
            stmt.setDouble(9,vo.getFracaoIdeal());
            stmt.setString(10,vo.getCpfInquilino());
            stmt.setString(11,vo.getCodInquilino());

            stmt.execute();
            //pk = vo.getCodinquilino().toString();
            inquilinoFrame.reloadData();
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

    /**
     * Method called by the Form panel to update existing data.
     * @param oldPersistentObject original value object, previous to the changes
     * @param persistentObject value object to save
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update inquilino set condominio_cod_cond=?," +
                    "apto_inq=?,bloco_inq=?,quadra_inq=?,nome_inq=?,fone_inq=?,celular_inq=?," +
                    "vlr_cota_normal_inq=?,fracao_ideal_inq=?,cpf_inq=?,cod_inq=? where cod_inq=?");
            InquilinoVO vo = (InquilinoVO) persistentObject;

            stmt.setInt(1,vo.getCodCondominio());
            stmt.setString(2,vo.getApto());
            stmt.setString(3,vo.getBloco());
            stmt.setString(4,vo.getQuadra());
            stmt.setString(5,vo.getNomeInquilino());
            stmt.setString(6,vo.getFoneInquilino());
            stmt.setString(7,vo.getCelularInquilino());
            stmt.setDouble(8,vo.getCotaNormal());
            stmt.setDouble(9,vo.getFracaoIdeal());
            stmt.setString(10,vo.getCpfInquilino());
            stmt.setString(11,vo.getCodInquilino());
            stmt.setString(12,vo.getCodInquilino());

            stmt.execute();
            inquilinoFrame.reloadData();
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

    /**
     * Method called by the Form panel to delete existing data.
     * @param persistentObject value object to delete
     * @return an ErrorResponse value object in case of errors, VOResponse if the operation is successfully completed
     */
    public Response deleteRecord(ValueObject persistentObject) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from inquilino where cod_inq=?");
            InquilinoVO vo = (InquilinoVO) persistentObject;
            stmt.setString(1, vo.getCodInquilino());
            stmt.execute();
            inquilinoFrame.reloadData();
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
}