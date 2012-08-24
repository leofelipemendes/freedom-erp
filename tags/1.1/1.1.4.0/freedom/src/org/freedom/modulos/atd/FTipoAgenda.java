/**
 * @version 02/11/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Alex Rodrigues <BR>
 *
 * Projeto: Freedom <BR>
 *  
 * Pacote: org.freedom.modulos.grh <BR>
 * Classe: @(#)FTipoAgenda.java <BR>
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
 * Tela de cadastro de tipos de agendamento
 * 
 */

package org.freedom.modulos.atd; 

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JLabel;

import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.componentes.Navegador;
import org.freedom.telas.FDialogo;

public class FTipoAgenda extends FDialogo {
	
	private static final long serialVersionUID = 1L;
	private JTextFieldPad txtCodTipoAGD= new JTextFieldPad(JTextFieldPad.TP_INTEGER,5,0);
	private JTextFieldPad txtDescTipoAGD= new JTextFieldPad(JTextFieldPad.TP_STRING,40,0);
	public ListaCampos lcCampos = new ListaCampos(null); 
	public Navegador nav = new Navegador(false); 
	  
	public FTipoAgenda () {
		  
		super();
		setTitulo("Tipos de Agendamentos", this.getClass().getName() );
		setAtribos( 345, 125 );
		setToFrameLayout();
		
		pnRodape.add(nav, BorderLayout.WEST);
		nav.setListaCampos(lcCampos);
		lcCampos.setNavegador(nav);
		
		lcCampos.add(new GuardaCampo( txtCodTipoAGD, "CodTipoAGD", "C�d.tp.agd.", ListaCampos.DB_PK, true));
		lcCampos.add(new GuardaCampo( txtDescTipoAGD, "DescTipoAGD", "Descri��o do tipo de agendamento", ListaCampos.DB_SI,true));
		lcCampos.montaSql(true, "TIPOAGENDA", "SG");    
		lcCampos.setReadOnly(false);
		txtCodTipoAGD.setTabelaExterna(lcCampos);
		txtCodTipoAGD.setFK(true);
		txtCodTipoAGD.setNomeCampo("CodTipoAGD");
		
		adic(new JLabel("C�d.tp.agd."), 7, 10, 70, 20);
		adic(txtCodTipoAGD, 7, 30, 70, 20);
		adic(new JLabel("Descri��o do tipo de agendamento"), 80, 10, 240, 20);
		adic(txtDescTipoAGD, 80, 30, 240, 20);
		    
		lcCampos.setQueryInsert(false);
	    
	}
	
	public void setConexao(Connection con) {
		//super.setConexao(con);
		lcCampos.setConexao(con);
	}
  
}