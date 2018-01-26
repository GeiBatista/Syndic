package br.com.syndic.main;

import org.openswing.swing.tree.java.OpenSwingTreeNode;
import java.util.*;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.util.client.ClientSettings;
import org.openswing.swing.internationalization.java.EnglishOnlyResourceFactory;
import org.openswing.swing.util.client.*;
import org.openswing.swing.permissions.client.*;
import java.awt.Image;
import javax.swing.*;
import org.openswing.swing.internationalization.java.Language;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.internationalization.java.XMLResourcesFactory;
import java.sql.*;
import org.openswing.swing.domains.java.Domain;
import org.openswing.swing.internationalization.java.*;
import org.openswing.swing.util.java.Consts;

/**
 * <p>
 * Title: OpenSwing Demo</p>
 * <p>
 * Description: Used to start application from main method: it creates an MDI
 * Frame app.</p>
 * <p>
 * Copyright: Copyright (C) 2006 Mauro Carniel</p>
 * <p>
 * </p> @author Mauro Carniel
 *
 * @version 1.0
 */
public class Menu implements MDIController, LoginController {

    private ClienteFachada clientFacade = null;
    private Connection conn = null;
    private Hashtable domains = new Hashtable();
    private String username = null;
    private Properties idiomas = new Properties();

    public Menu() {
        createConnection();
        clientFacade = new ClienteFachada(conn);

        Hashtable xmlFiles = new Hashtable();
        xmlFiles.put("EN", "recursos/Resources_en.xml");
        xmlFiles.put("IT", "recursos/Resources_it.xml");
        xmlFiles.put("PT_BR", "recursos/Resources_pt_br.xml");

        ClientSettings clientSettings = new ClientSettings(
                new XMLResourcesFactory(xmlFiles, false),
                domains);

//    ClientSettings.BACKGROUND = "background4.jpg";
//    ClientSettings.TREE_BACK = "treeback2.jpg";
//    ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
//    ClientSettings.VIEW_MANDATORY_SYMBOL = true;
//    ClientSettings.ALLOW_OR_OPERATOR = false;
//    ClientSettings.INCLUDE_IN_OPERATOR = false;
//    ClientSettings.SHOW_SCROLLBARS_IN_MDI = true;
//    ClientSettings.IGNORE_GRID_SELECTION_FOREGROUND = true;
        ClientSettings.BACKGROUND = "fundo3.png";
        ClientSettings.BACK_IMAGE_DISPOSITION = Consts.BACK_IMAGE_CENTERED;
        ClientSettings.TREE_BACK = "t2ti.jpg";
        ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
        ClientSettings.VIEW_MANDATORY_SYMBOL = true;
        ClientSettings.ALLOW_OR_OPERATOR = false;
        ClientSettings.INCLUDE_IN_OPERATOR = false;
        ClientSettings.SHOW_SCROLLBARS_IN_MDI = false;
        ClientSettings.FILTER_PANEL_ON_GRID = true;

        ClientSettings.getInstance().setLanguage("PT_BR");

        idiomas.setProperty("EN", "English");
        idiomas.setProperty("IT", "Italiano");
        idiomas.setProperty("PT_BR", "Português do Brasil");

        LoginDialog d = new LoginDialog(null, false, this);
    }

    /**
     * Method called after MDI creation.
     */
    public void afterMDIcreation(MDIFrame frame) {
        GenericStatusPanel userPanel = new GenericStatusPanel();
        userPanel.setColumns(12);
        MDIFrame.addStatusComponent(userPanel);
        userPanel.setText(username);
        MDIFrame.addStatusComponent(new Clock());
    }

    /**
     * @see JFrame getExtendedState method
     */
    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    /**
     * @return client facade, invoked by the MDI Frame tree/menu
     */
    public ClientFacade getClientFacade() {
        return clientFacade;
    }

    /**
     * Method used to destroy application.
     */
    public void stopApplication() {
        System.exit(0);
    }

    /**
     * Defines if application functions must be viewed inside a tree panel of
     * MDI Frame.
     *
     * @return <code>true</code> if application functions must be viewed inside
     * a tree panel of MDI Frame, <code>false</code> no tree is viewed
     */
    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    /**
     * Defines if application functions must be viewed in the menubar of MDI
     * Frame.
     *
     * @return <code>true</code> if application functions must be viewed in the
     * menubar of MDI Frame, <code>false</code> otherwise
     */
    public boolean viewFunctionsInMenuBar() {
        return true;
    }

    /**
     * @return <code>true</code> if the MDI frame must show a login menu in the
     * menubar, <code>false</code> no login menu item will be added
     */
    public boolean viewLoginInMenuBar() {
        return true;
    }

    /**
     * @return application title
     */
    public String getMDIFrameTitle() {
        return "LUNA - Sistema para Administração de Condomínios";
    }

    /**
     * @return text to view in the about dialog window
     */
    public String getAboutText() {
        return "Luna - Sistema para Administraçãoo de Condomínios\n"
                + "\n"
                + "Copyright: Copyright (C) 2018 Mr.Bartista.com\n"
                + "Autor: Gei Batista";

    }

    /**
     * @return image name to view in the about dialog window
     */
    public String getAboutImage() {
        return "LogoMrBartista250.png";
    }

    /**
     * @param parentFrame parent frame
     * @return a dialog window to logon the application; the method can return
     * null if viewLoginInMenuBar returns false
     */
    public JDialog viewLoginDialog(JFrame parentFrame) {
        JDialog d = new LoginDialog(parentFrame, true, this
        //        ClientSettings.getInstance().getResources().getLanguageId()
        );
        return d;
    }

    /**
     * @return maximum number of failed login
     */
    public int getMaxAttempts() {
        return 3;
    }

    /**
     * Method called by MDI Frame to authenticate the user.
     *
     * @param loginInfo login information, like username, password, ...
     * @return <code>true</code> if user is correcly authenticated,
     * <code>false</code> otherwise
     */
    public boolean authenticateUser(Map loginInfo) throws Exception {

        if ("ADMIN".equalsIgnoreCase((String) loginInfo.get("username"))
                && "ADMIN".equalsIgnoreCase((String) loginInfo.get("password"))
                || "MAURO".equalsIgnoreCase((String) loginInfo.get("username"))
                && "MAURO".equalsIgnoreCase((String) loginInfo.get("password"))
                || "GEI".equalsIgnoreCase((String) loginInfo.get("username"))
                && "1234".equalsIgnoreCase((String) loginInfo.get("password"))) {
            return true;
        } else {
            return false;

        }
    }

    public static void main(String[] argv) {
        new Menu();
    }

    /**
     * Method called by LoginDialog to notify the sucessful login.
     *
     * @param loginInfo login information, like username, password, ...
     */
    public void loginSuccessful(Map loginInfo) {
        username = loginInfo.get("username").toString().toUpperCase();
        if (username.equals("ADMIN")) {
            ClientSettings.getInstance().setLanguage("EN");
        } else if (username.equals("MAURO")) {
            ClientSettings.getInstance().setLanguage("IT");
        } else if (username.equals("GEI")) {
            ClientSettings.getInstance().setLanguage("PT_BR");
        }

        Domain orderStateDomain = new Domain("ORDERSTATE");
        orderStateDomain.addDomainPair("O", "opened");
        orderStateDomain.addDomainPair("S", "suspended");
        orderStateDomain.addDomainPair("D", "delivered");
        orderStateDomain.addDomainPair("ABC", "closed");
        domains.clear();
        domains.put(
                orderStateDomain.getDomainId(),
                orderStateDomain
        );

        MDIFrame mdi = new MDIFrame(this);
//    try {
//      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//      SwingUtilities.updateComponentTreeUI(mdi);
//    }
//    catch (Throwable ex) {
//      ex.printStackTrace();
//    }

        //configura os botões da barra de ferramentas
        mdi.addButtonToToolBar("condominio1.png", "Cadastro de Condomínios");
        mdi.addButtonToToolBar("fornecedor1.png", "Cadastro de Fornecedores");
        mdi.addButtonToToolBar("banco.png", "Cadastro de Bancos");
        mdi.addButtonToToolBar("inquilino3.png", "Cadastro de Inquilinos");
        mdi.addSeparatorToToolBar();
        mdi.addSeparatorToToolBar();
        mdi.addButtonToToolBar("plano_conta1.png", "Cadastro do Centro de Custo (Contas)");
        mdi.addButtonToToolBar("contrato1.png", "Cadastro dos Contratos com Fornecedores");
        mdi.addSeparatorToToolBar();
        mdi.addSeparatorToToolBar();
        mdi.addButtonToToolBar("boleto1.png", "Emissão de Boletos");
        mdi.addButtonToToolBar("gas1.png", "Controle do Gás");
        mdi.addSeparatorToToolBar();
        mdi.addSeparatorToToolBar();
        mdi.addButtonToToolBar("pagar1.png", "Contas a Pagar");
        mdi.addButtonToToolBar("receber1.png", "Confirma Recebimentos");
        mdi.addButtonToToolBar("cheque1.png", "Conciliação de Cheques");
        mdi.addButtonToToolBar("bancario1.png", "Movimento Bancário");
        mdi.addSeparatorToToolBar();
        mdi.addSeparatorToToolBar();
        mdi.addButtonToToolBar("ata1.png", "Controle de Atas");
        mdi.addButtonToToolBar("carta1.png", "Cartas de Cobrança");
        mdi.addSeparatorToToolBar();
        mdi.addSeparatorToToolBar();
        mdi.addButtonToToolBar("sair1.png", "Sair da Aplicação");
    }

    /**
     * @return <code>true</code> if the MDI frame must show a change language
     * menu in the menubar, <code>false</code> no change language menu item will
     * be added
     */
    public boolean viewChangeLanguageInMenuBar() {
        return true;
    }

    /**
     * @return list of languages supported by the application
     */
    public ArrayList getLanguages() {
        ArrayList list = new ArrayList();
        list.add(new Language("EN", "English"));
        list.add(new Language("IT", "Italiano"));
        list.add(new Language("PT_BR", "Portugês do Brasil"));
        return list;
    }

    /**
     * @return application functions (ApplicationFunction objects), organized as
     * a tree
     */
    public DefaultTreeModel getApplicationFunctions() {
        DefaultMutableTreeNode root = new OpenSwingTreeNode();

        DefaultTreeModel model = new DefaultTreeModel(root);

        ApplicationFunction n1 = new ApplicationFunction("Cadastro", null);
        ApplicationFunction n2 = new ApplicationFunction("Movimento", null);

        ApplicationFunction n11 = new ApplicationFunction("Condomínios", "condominio", null, "getCondominio");
        ApplicationFunction n12 = new ApplicationFunction("Inquilinos", "inquilino", null, "getInquilino");
        ApplicationFunction n13 = new ApplicationFunction("Bancos", "banco", null, "getBanco");
        ApplicationFunction n14 = new ApplicationFunction("Fornecedores", "fornecedor", null, "getFornecedor");
        ApplicationFunction n15 = new ApplicationFunction(true);
        ApplicationFunction n16 = new ApplicationFunction("Centro de Custo", "centro_custo", null, "getCentroCusto");
        ApplicationFunction n17 = new ApplicationFunction("Contratos", "contrato", null, "getContrato");

        ApplicationFunction n21 = new ApplicationFunction("Contas a Receber", null);
        ApplicationFunction n211 = new ApplicationFunction("Emissao de Boletos", "boleto", null, "getBoleto");
        ApplicationFunction n212 = new ApplicationFunction("Confirma Recebimentos", "receber", null, "getReceber");
        ApplicationFunction n22 = new ApplicationFunction("Contas a Pagar", "pagar", null, "getPagar");
        ApplicationFunction n23 = new ApplicationFunction("Controle do Gás", "gas", null, "getGas");
        ApplicationFunction n24 = new ApplicationFunction("Conciliação de Cheques", "cheque", null, "getCheque");
        ApplicationFunction n25 = new ApplicationFunction("Movimento Bancário", "bancario", null, "getBancario");
        ApplicationFunction n26 = new ApplicationFunction(true);
        ApplicationFunction n27 = new ApplicationFunction("Controle de Atas", "ata", null, "getAta");
        ApplicationFunction n28 = new ApplicationFunction("Cartas de Cobrança", "cobranca", null, "getCobranca");

        n1.add(n11);
        n1.add(n12);
        n1.add(n13);
        n1.add(n14);
        n1.add(n15);
        n1.add(n16);
        n1.add(n17);

        n2.add(n21);
        n21.add(n211);
        n21.add(n212);
        n2.add(n22);
        n2.add(n23);
        n2.add(n24);
        n2.add(n25);
        n2.add(n26);
        n2.add(n27);
        n2.add(n28);

        root.add(n1);
        root.add(n2);

        return model;
    }

    /**
     * Create the database connection (using Hypersonic DB - in memory) and
     * initialize tables...
     */
//    private void createConnection() {
//        try {
//            Class.forName("org.hsqldb.jdbcDriver");
//            conn = DriverManager.getConnection("jdbc:hsqldb:mem:" + "a" + Math.random(), "sa", "");
//            PreparedStatement stmt = null;
//            try {
//                stmt = conn.prepareStatement("create table DEMO2(TEXT VARCHAR(20),DECNUM DECIMAL(10,2),CURRNUM DECIMAL(10,2),THEDATE DATE,COMBO VARCHAR,CHECK_BOX CHAR(1),RADIO CHAR(1),CODE VARCHAR,TA VARCHAR,PRIMARY KEY(TEXT))");
//                stmt.execute();
//                stmt.close();
//
//                stmt = conn.prepareStatement("create table DEMO2_LOOKUP(CODE VARCHAR,DESCRCODE VARCHAR,PRIMARY KEY(CODE))");
//                stmt.execute();
//
//                for (int i = 0; i < 10; i++) {
//                    stmt.close();
//                    stmt = conn.prepareStatement("insert into DEMO2 values('ABC" + i + "'," + 12 + i + 0.333 + "," + 1234 + i + 0.56 + ",?,'ABC','Y','Y','A" + i + "','AAAAAA" + i + "')");
//                    stmt.setObject(1, new java.sql.Date(System.currentTimeMillis() + 86400000 * i));
//                    stmt.execute();
//                }
//
//                for (int i = 0; i < 10; i++) {
//                    stmt.close();
//                    stmt = conn.prepareStatement("insert into DEMO2_LOOKUP values('A" + i + "','ABCDEF" + String.valueOf((char) (i + 78)) + "')");
//                    stmt.execute();
//                }
//
//            } catch (SQLException ex1) {
//                ex1.printStackTrace();
//            } finally {
//                try {
//                    stmt.close();
//                } catch (SQLException ex2) {
//                }
//            }
//
//            conn.commit();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    private void createConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/syndic", "root", "gbs123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return <code>true</code> if the MDI frame must show a panel in the
     * bottom, containing last opened window icons, <code>false</code> no panel
     * is showed
     */
    public boolean viewOpenedWindowIcons() {
        return true;
    }

    /**
     * @return <code>true</code> if the MDI frame must show the "File" menu in
     * the menubar of the frame, <code>false</code> to hide it
     */
    public boolean viewFileMenu() {
        return true;
    }

}
