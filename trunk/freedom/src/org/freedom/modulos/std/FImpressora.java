/**
 * @version 11/02/2002 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 *
 * Projeto: Freedom <BR>
 *  
 * Pacote: org.freedom.modulos.std <BR>
 * Classe: @(#)FImpressora.java <BR>
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
 * Coment�rios sobre a classe...
 * 
 */

package org.freedom.modulos.std;
import org.freedom.telas.FDados;
import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JComboBoxPad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JComboBox;
import java.util.Vector;
public class FImpressora extends FDados {
  private JTextFieldPad txtCodImp = new JTextFieldPad(8);
  private JTextFieldPad txtDescImp = new JTextFieldPad(40);
  private JTextFieldPad txtTipoImp = new JTextFieldPad(2);
  private JTextFieldPad txtLinPagImp = new JTextFieldPad(8);
  private JTextFieldPad txtNSerieImp = new JTextFieldPad(15);
  private JTextFieldPad txtPortaWinImp = new JTextFieldPad(4);
  private JTextFieldPad txtPortaLinImp = new JTextFieldPad(20);
  private JTextFieldPad txtCodPapel = new JTextFieldPad(20);
  private JTextFieldFK txtDescPapel = new JTextFieldFK();
  private JComboBox cbTipoImp = new JComboBox();
  private JComboBoxPad cbDestImp = null;
  private Vector vVals = new Vector();
  private Vector vLabs = new Vector();
  private ListaCampos lcPapel = new ListaCampos(this,"PL");
  public FImpressora() {
//Remove o painel de impress�o:
    pnRodape.remove(2);
//Constroi a tela FImpressoras:
    setTitulo("Cadastro de impressoras");
    setAtribos( 50, 50, 400, 280);

//Prepara o Combo para alterar o campo txtTipoImp    
    cbTipoImp.addItem("");
    cbTipoImp.addItem("Epson Matricial");
    cbTipoImp.addItem("HP Desk Jet");
    cbTipoImp.addItem("HP Laser Jet");
    cbTipoImp.addItem("Epson Stylus");
    cbTipoImp.addItem("Epson Laser");
    cbTipoImp.addItem("Fiscal MP20");
    cbTipoImp.addItem("Fiscal MP40");
    cbTipoImp.setEditable(false);
    cbTipoImp.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          if (evt.getSource() == cbTipoImp) {
            if (((JComboBox) evt.getSource()).getSelectedIndex() == 0) {
              txtTipoImp.setVlrString("");
            }  
            else {
              txtTipoImp.setVlrString(""+((JComboBox) evt.getSource()).getSelectedIndex());
            }
          }
        }
      }
    );
    
    vLabs.addElement("Nota Fiscal");           
    vLabs.addElement("Nota Fiscal - Servi�o");           
    vLabs.addElement("Pedido");           
    vLabs.addElement("Relat�rio Simples");           
    vLabs.addElement("Relat�rio Gr�fico");           
    vLabs.addElement("Todos (n�o NF)");           
               
    vVals.addElement("NF");           
    vVals.addElement("NS");           
    vVals.addElement("PD");           
    vVals.addElement("RS");           
    vVals.addElement("RG");           
    vVals.addElement("TO");           
    
    cbDestImp = new JComboBoxPad(vLabs,vVals);
    cbDestImp.setVlrString("TO");

    txtTipoImp.setEditable(false);
    txtTipoImp.addActionListener( 
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          if (evt.getSource() == txtTipoImp) {
            if (txtTipoImp.getText().trim().length() == 1) {
              cbTipoImp.setSelectedIndex(Integer.parseInt(txtTipoImp.getText().trim()));
            }
            else 
              cbTipoImp.setSelectedIndex(0);
          }
        }
      }
    );
//Prepara FKs
    txtCodPapel.setTipo(JTextFieldPad.TP_STRING,20,0);
    txtDescPapel.setTipo(JTextFieldPad.TP_STRING,40,0);    

    lcPapel.add(new GuardaCampo( txtCodPapel, 7, 100, 80, 20, "CodPapel", "C�digo", true, false, null, JTextFieldPad.TP_STRING,true),"txtCodPapelx");
    lcPapel.add(new GuardaCampo( txtDescPapel, 90, 100, 207, 20, "DescPapel", "Descri��o", false, false, null, JTextFieldPad.TP_STRING,false),"txtDescPapelx");
    lcPapel.montaSql(false, "PAPEL", "SG");    
    lcPapel.setQueryCommit(false);
    lcPapel.setReadOnly(true);
    txtCodPapel.setTabelaExterna(lcPapel);
//Adiciona componentes   
    adicCampo(txtCodImp, 7, 20, 80, 20, "CodImp", "C�digo", JTextFieldPad.TP_INTEGER, 8, 0, true, false, null, true);
    adicCampo(txtDescImp, 90, 20, 286, 20, "DescImp", "Descri��o", JTextFieldPad.TP_STRING, 40, 0, false, false, null, true);
    adicCampo(txtTipoImp, 7, 60, 80, 20, "TipoImp", "Tipo", JTextFieldPad.TP_STRING, 2, 0, false, false, null, true);
    pinDados.adic(cbTipoImp,90,60,286,20);
    adicCampo(txtLinPagImp, 7, 100, 90, 20, "LinPagImp", "Linhas Pag.", JTextFieldPad.TP_INTEGER, 8, 0, false, false, null, true);
    adicCampo(txtNSerieImp, 100, 100, 90, 20, "NSerieImp", "Num. Serie", JTextFieldPad.TP_STRING, 15, 0, false, false, null, false);
    adicCampo(txtPortaWinImp, 193, 100, 90, 20, "PortaWinImp", "Porta WIN", JTextFieldPad.TP_STRING, 4, 0, false, false, null, true);
    adicCampo(txtPortaLinImp, 286, 100, 90, 20, "PortaLinImp", "Nome LIN", JTextFieldPad.TP_STRING, 60, 0, false, false, null, true);
    adicCampo(txtCodPapel, 7, 140, 80, 20, "CodPapel", "C�digo", JTextFieldPad.TP_STRING, 20, 0, false, true, null, true);
    adicDescFK(txtDescPapel, 90, 140, 286, 20, "DescPapel", "e descri��o do tipo de papel", JTextFieldPad.TP_STRING, 40, 0);
    adicDB(cbDestImp, 7, 180, 200, 25, "DestImp", "Padr�o para",JTextFieldPad.TP_STRING,true);

    setListaCampos(true, "IMPRESSORA", "SG");
  }
  public void execShow(Connection cn) {
    lcPapel.setConexao(cn);      
    super.execShow(cn);
  }
}
