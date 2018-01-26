package br.com.syndic.main;

import br.com.syndic.controller.BancoController;
import br.com.syndic.controller.CondominioController;
import br.com.syndic.controller.InquilinoController;
import br.com.syndic.controller.RateioGasController;
import br.com.syndic.frame.EmissaoBoletos;
import br.com.syndic.frame.MovimentoBancario;
import br.com.syndic.frame.Pagamentos;
import br.com.syndic.frame.Recebimentos;
import org.openswing.swing.mdi.client.*;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 * <p>Title: OpenSwing Framework</p>
 * <p>Description: Client Facade, called by the MDI Tree.</p>
 * <p>Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p> </p>
 * @author Mauro Carniel
 * @version 1.0
 */
public class ClienteFachada implements ClientFacade {

  private Connection conn = null;

  public ClienteFachada(Connection conn) {
    this.conn = conn;
  }

  public void getBanco() {
        new BancoController(conn);
    }

  public void getCondominio() {
        new CondominioController(conn);
    }
  
  

    public void getInquilino() {
        new InquilinoController(conn);
    }

    public void getGas() {
        new RateioGasController(conn);
    }

    public void getBoleto() {
        EmissaoBoletos boleto = new EmissaoBoletos(conn);
        MDIFrame.add(boleto, true);
    }

    public void getReceber() {
        Recebimentos rec = new Recebimentos(conn);
        MDIFrame.add(rec, true);
    }

    public void getPagar() {
        Pagamentos pag = new Pagamentos(conn);
        MDIFrame.add(pag, true);
    }

    public void getCheque() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getAta() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getCobranca() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getContrato() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getCentroCusto() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getFornecedor() {
        JOptionPane.showMessageDialog(null, "Em Construção");
    }

    public void getBancario() {
        MovimentoBancario banco = new MovimentoBancario(conn);
        MDIFrame.add(banco, true);
    }


  public void getF1() {
     // new GridFrameController(conn);
  }


//  public void getF2() {
//    InternalFrame f = new InternalFrame();
//    f.setSize(300,200);
//    f.setTitle("Function2");
//    MDIFrame.add(f);
//  }
//
//
//  public void getF3() {
//    InternalFrame f = new InternalFrame();
//    f.setSize(300,200);
//    f.setTitle("Function3");
//    MDIFrame.add(f);
//  }
//
//
//  public void getF4() {
//    InternalFrame f = new InternalFrame();
//    f.setSize(300,200);
//    f.setTitle("Function4");
//    MDIFrame.add(f);
//  }
//
//
//  public void getF5() {
//    InternalFrame f = new InternalFrame();
//    f.setSize(300,200);
//    f.setTitle("Function5");
//    MDIFrame.add(f);
//  }

}