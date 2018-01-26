package br.com.syndic.controller;

import br.com.syndic.frame.Condominio;
import br.com.syndic.frame.CondominioDetalhe;
import br.com.syndic.vo.CondominioVO;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.util.java.Consts;
import java.sql.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.mdi.client.MDIFrame;

public class CondominioDetalheController extends FormController {

    private CondominioDetalhe frame = null;
    private Connection conn = null;
    private String pk = null;
    private Condominio condominioFrame = null;

    public CondominioDetalheController(Condominio condominioFrame, String pk, Connection conn) {
        this.condominioFrame = condominioFrame;
        this.pk = pk;
        this.conn = conn;
        frame = new CondominioDetalhe(conn, this);
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
            ResultSet rset = stmt.executeQuery("select condominio.cod_cond ,condominio.nome_cond,condominio.endereco_cond,condominio.bairro_cond, " +
                    "cidade_cond,uf_cond,cep_cond,fone_cond,fax_cond,cnpj_cond,email_cond,sindico_cond," +
                    "instrucao1,instrucao2,instrucao3,instrucao4,mora_cond,multa_cond,rateio_cond,dia_vencimento_cond from condominio where cod_cond=" + pk);
            if (rset.next()) {
                CondominioVO vo = new CondominioVO();

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
            stmt = conn.prepareStatement("insert into condominio" +
                    "(nome_cond,endereco_cond,bairro_cond,cidade_cond,uf_cond,cep_cond,fone_cond,fax_cond,cnpj_cond,email_cond,sindico_cond," +
                    "instrucao1,instrucao2,instrucao3,instrucao4,mora_cond,multa_cond,rateio_cond,dia_vencimento_cond) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            CondominioVO vo = (CondominioVO) newPersistentObject;

            stmt.setString(1, vo.getNomeCondominio());
            stmt.setString(2, vo.getEnderecoCondominio());
            stmt.setString(3, vo.getBairroCondominio());
            stmt.setString(4, vo.getCidadeCondominio());
            stmt.setString(5, vo.getUfCondominio());
            stmt.setString(6, vo.getCepCondominio());
            stmt.setString(7, vo.getFoneCondominio());
            stmt.setString(8, vo.getFaxCondominio());
            stmt.setString(9, vo.getCnpjCondominio());
            stmt.setString(10, vo.getEmailCondominio());
            stmt.setString(11, vo.getSindicoCondominio());
            stmt.setString(12, vo.getInstrucao1());
            stmt.setString(13, vo.getInstrucao2());
            stmt.setString(14, vo.getInstrucao3());
            stmt.setString(15, vo.getInstrucao4());
            stmt.setDouble(16, vo.getMoraCondominio());
            stmt.setDouble(17, vo.getMultaCondominio());
            stmt.setDouble(18, vo.getRateioCondominio());
            stmt.setString(19, vo.getDiaVencimento());

            stmt.execute();

            //pk = vo.getCodCondominio().toString();
            condominioFrame.reloadData();
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
            stmt = conn.prepareStatement("update condominio set" +
                    " nome_cond=?,endereco_cond=?,bairro_cond=?,cidade_cond=?,uf_cond=?,cep_cond=?,fone_cond=?,fax_cond=?,cnpj_cond=?,email_cond=?,sindico_cond=?," +
                    "instrucao1=?,instrucao2=?,instrucao3=?,instrucao4=?,mora_cond=?,multa_cond=?,rateio_cond=?,dia_vencimento_cond=? where cod_cond=?");

            CondominioVO vo = (CondominioVO) persistentObject;

            stmt.setString(1, vo.getNomeCondominio());
            stmt.setString(2, vo.getEnderecoCondominio());
            stmt.setString(3, vo.getBairroCondominio());
            stmt.setString(4, vo.getCidadeCondominio());
            stmt.setString(5, vo.getUfCondominio());
            stmt.setString(6, vo.getCepCondominio());
            stmt.setString(7, vo.getFoneCondominio());
            stmt.setString(8, vo.getFaxCondominio());
            stmt.setString(9, vo.getCnpjCondominio());
            stmt.setString(10, vo.getEmailCondominio());
            stmt.setString(11, vo.getSindicoCondominio());
            stmt.setString(12, vo.getInstrucao1());
            stmt.setString(13, vo.getInstrucao2());
            stmt.setString(14, vo.getInstrucao3());
            stmt.setString(15, vo.getInstrucao4());
            stmt.setDouble(16, vo.getMoraCondominio());
            stmt.setDouble(17, vo.getMultaCondominio());
            stmt.setDouble(18, vo.getRateioCondominio());
            stmt.setString(19, vo.getDiaVencimento());
            stmt.setInt(20, vo.getCodCondominio());
            
            stmt.execute();
            condominioFrame.reloadData();
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
            stmt = conn.prepareStatement("delete from condominio where cod_cond=?");
            CondominioVO vo = (CondominioVO) persistentObject;
            stmt.setInt(1, vo.getCodCondominio());
            stmt.execute();
            condominioFrame.reloadData();
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