/**
 * @version 25/03/2004 <BR>
 * @author Setpoint Inform�tica Ltda./Anderson Sanchez <BR>
 *
 * Projeto: Freedom <BR>
 *  
 * Pacote: org.freedom.modulos.pcp <BR>
 * Classe: @(#)FBanco.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR> <BR>
 *
 * Tela de cadastro de tipos de recursos de produ��o.
 */

package org.freedom.modulos.pcp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.ImprimeOS;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.FDados;
public class FRecursos extends FDados implements ActionListener {
  private JTextFieldPad txtCodRecp = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
  private JTextFieldPad txtDescRecp = new JTextFieldPad(50);
  private JTextFieldPad txtCodTpRecp = new JTextFieldPad(JTextFieldPad.TP_INTEGER,8,0);
  private JTextFieldFK txtDescTpRecp = new JTextFieldFK(JTextFieldPad.TP_STRING,50,0);
  private ListaCampos lcTpRecp = new ListaCampos(this,"TR");
  public FRecursos () {
    setTitulo("Cadastro de recursos de produc�o.");
    setAtribos( 50, 50, 350, 165);

    lcTpRecp.add(new GuardaCampo( txtCodTpRecp, 7, 100, 80, 20, "Codtprec", "C�digo", true, false, txtDescTpRecp, JTextFieldPad.TP_INTEGER,true),"txtCodtpRec");
    lcTpRecp.add(new GuardaCampo( txtDescTpRecp, 7, 100, 80, 20, "Desctprec", "Descri�ao", false, false, null, JTextFieldPad.TP_STRING,false),"txtDesctpRec");
    lcTpRecp.montaSql(false, "TIPOREC", "PP");    
    lcTpRecp.setQueryCommit(false);
    lcTpRecp.setReadOnly(true);
    txtCodTpRecp.setTabelaExterna(lcTpRecp);
    
    adicCampo(txtCodRecp, 7, 20, 50, 20,"Codrecp","C�digo",JTextFieldPad.TP_INTEGER,8,0,true,false,null,true);
    adicCampo(txtDescRecp, 60, 20, 250, 20,"descrecp","Nome",JTextFieldPad.TP_STRING,40,0,false,false,null,true);
    adicCampo(txtCodTpRecp, 7, 60, 50, 20, "Codtprec", "C�digo", JTextFieldPad.TP_INTEGER, 8, 0, false, true, txtDescTpRecp,false);
    adicDescFK(txtDescTpRecp, 60, 60, 250, 20, "desctprec", "e descri��o do tipo de recurso", JTextFieldPad.TP_STRING, 50, 0);
    setListaCampos( false, "RECURSO", "PP");
    btImp.addActionListener(this);
    btPrevimp.addActionListener(this);
    lcCampos.setQueryInsert(false);    
  }
  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == btPrevimp) {
        imprimir(true);
    }
    else if (evt.getSource() == btImp) 
      imprimir(false);
    super.actionPerformed(evt);
  }

  private void imprimir(boolean bVisualizar) {
    ImprimeOS imp = new ImprimeOS("",con);
    int linPag = imp.verifLinPag()-1;
    imp.montaCab();
    imp.setTitulo("Relat�rio de Recursos de produ��o");
    DLRRecursos dl = new DLRRecursos(this);
    dl.setVisible(true);
    if (dl.OK == false) {
      dl.dispose();
      return;
    }
    String sSQL = "SELECT CODRECP,DESCRECP FROM PPRECURSO ORDER BY "+dl.getValor();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = con.prepareStatement(sSQL);
      rs = ps.executeQuery();
      imp.limpaPags();
      while ( rs.next() ) {
         if (imp.pRow()==0) {
            imp.impCab(80);
            imp.say(imp.pRow()+0,0,""+imp.normal());
            imp.say(imp.pRow()+0,0,"");
            imp.say(imp.pRow()+0,2,"C�digo");
            imp.say(imp.pRow()+0,30,"Descri��o");
            imp.say(imp.pRow()+1,0,""+imp.normal());
            imp.say(imp.pRow()+0,0,Funcoes.replicate("-",80));
         }
         imp.say(imp.pRow()+1,0,""+imp.normal());
         imp.say(imp.pRow()+0,2,rs.getString("Codrecp"));
         imp.say(imp.pRow()+0,30,rs.getString("descrecp"));
         if (imp.pRow()>=linPag) {
            imp.incPags();
            imp.eject();
         }
      }
      
      imp.say(imp.pRow()+1,0,""+imp.normal());
      imp.say(imp.pRow()+0,0,Funcoes.replicate("=",80));
      imp.eject();
      
      imp.fechaGravacao();
      if (!con.getAutoCommit())
      	con.commit();
      dl.dispose();
    }  
    catch ( SQLException err ) {
		Funcoes.mensagemErro(this,"Erro consulta tabela de recursos de produ��o!"+err.getMessage());      
    }
    
    if (bVisualizar) {
      imp.preview(this);
    }
    else {
      imp.print();
    }
  }
  public void execShow(Connection cn) {
  	lcTpRecp.setConexao(cn);
  	super.execShow(cn);
  }
}
