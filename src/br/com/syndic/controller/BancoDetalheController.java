package br.com.syndic.controller;

import br.com.syndic.frame.BancoDetalhe;
import br.com.syndic.frame.Banco;
import br.com.syndic.vo.BancoVO;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.util.java.Consts;
import java.sql.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.mdi.client.MDIFrame;

public class BancoDetalheController extends FormController {

    private BancoDetalhe frame = null;
    private Connection conn = null;
    private String pk = null;
    private Banco bancoFrame = null;

    public BancoDetalheController(Banco bancoFrame, String pk, Connection conn) {
        this.bancoFrame = bancoFrame;
        this.pk = pk;
        this.conn = conn;
        frame = new BancoDetalhe(conn, this);
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
            ResultSet rset = stmt.executeQuery("select banco.cod_banco,banco.cod_compensacao,banco.nome_banco,banco.site_banco from banco where cod_banco=" + pk);
            if (rset.next()) {
                BancoVO vo = new BancoVO();
                vo.setCodBanco(rset.getInt(1));
                vo.setCodCompensacao(rset.getInt(2));
                vo.setNomeBanco(rset.getString(3));
                vo.setSiteBanco(rset.getString(4));
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
            stmt = conn.prepareStatement("insert into banco(cod_compensacao,nome_banco,site_banco) values(?,?,?)");
            BancoVO vo = (BancoVO) newPersistentObject;
            stmt.setInt(1, vo.getCodCompensacao());
            stmt.setString(2, vo.getNomeBanco());
            stmt.setString(3, vo.getSiteBanco());
            stmt.execute();
            //pk = vo.getCodBanco().toString();
            bancoFrame.reloadData();
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
            stmt = conn.prepareStatement("update banco set cod_compensacao=?,nome_banco=?,site_banco=? where cod_banco=?");
            BancoVO vo = (BancoVO) persistentObject;
            stmt.setInt(1, vo.getCodCompensacao());
            stmt.setString(2, vo.getNomeBanco());
            stmt.setString(3, vo.getSiteBanco());
            stmt.setInt(4, vo.getCodBanco());
            stmt.execute();
            bancoFrame.reloadData();
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
            stmt = conn.prepareStatement("delete from banco where cod_banco=?");
            BancoVO vo = (BancoVO) persistentObject;
            stmt.setInt(1, vo.getCodBanco());
            stmt.execute();
            bancoFrame.reloadData();
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