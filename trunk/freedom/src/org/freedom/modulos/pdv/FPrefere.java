/**
 * @version 23/03/2004 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 *
 * Projeto: Freedom <BR>
 *  
 * Pacote: org.freedom.modulos.pdv <BR>
 * Classe: @(#)FPrefere.java <BR>
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
 * Tela para cadastro de prefer�ncias do PDV
 * 
 */


package org.freedom.modulos.pdv;
import java.sql.Connection;

import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.componentes.Painel;
import org.freedom.telas.FTabDados;
public class FPrefere extends FTabDados {
	private Painel pinVenda = new Painel();
	private JTextFieldPad txtCodTipoMov = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8 , 0);
	private JTextFieldFK txtDescTipoMov= new JTextFieldFK(JTextFieldPad.TP_STRING, 50 , 0);
	private JTextFieldPad txtCodPlanoPag = new JTextFieldPad(JTextFieldPad.TP_INTEGER, 8 , 0);
	private JTextFieldFK txtDescPlanoPag= new JTextFieldFK(JTextFieldPad.TP_STRING, 50 , 0);
	private ListaCampos lcTipoMov = new ListaCampos(this,"TM");
	private ListaCampos lcPlanoPag = new ListaCampos(this,"PP");
	public FPrefere() {
		setTitulo("Prefer�ncias do PDV");
		setAtribos(50, 50, 355, 375);
		
		lcTipoMov.add(new GuardaCampo(txtCodTipoMov,"CodTipoMov","C.TipoMov.",ListaCampos.DB_PK,true));
		lcTipoMov.add(new GuardaCampo(txtDescTipoMov,"DescTipoMov","Descri��o",ListaCampos.DB_SI,false));
		lcTipoMov.montaSql(false, "TIPOMOV", "EQ");
		lcTipoMov.setReadOnly(true);
		txtCodTipoMov.setTabelaExterna(lcTipoMov);
		txtCodTipoMov.setFK(true);
		txtCodTipoMov.setNomeCampo("CodTipoMov");
		
		lcPlanoPag.add(new GuardaCampo(txtCodPlanoPag,"CodPlanoPag","C.Plano Pag.",ListaCampos.DB_PK,true));
		lcPlanoPag.add(new GuardaCampo(txtDescPlanoPag,"DescPlanoPag","Descri��o",ListaCampos.DB_SI,false));
		lcPlanoPag.montaSql(false, "PLANOPAG", "FN");
		lcPlanoPag.setReadOnly(true);
		txtCodPlanoPag.setTabelaExterna(lcPlanoPag);

		setPainel(pinVenda);
		adicTab("Venda", pinVenda);
		adicCampo(txtCodTipoMov,10,30,77,20,"CodTipoMov","C.Tipo Mov.",ListaCampos.DB_FK,true);
		adicDescFK(txtDescTipoMov,90,30,230,20,"DescTipoMov","descri��o do tipo de movimento",JTextFieldPad.TP_STRING,50,0);
		adicCampo(txtCodPlanoPag,10,70,77,20,"CodPlanoPag","C. Plano Pag.",ListaCampos.DB_FK,true);
		adicDescFK(txtDescPlanoPag,90,70,230,20,"DescPlanoPag","descri��o do plano de pagamento",JTextFieldPad.TP_STRING,50,0);
		setListaCampos(false, "PREFERE4", "SG");
		
		nav.setAtivo(0,false);
		nav.setAtivo(1,false);
	}
	public void execShow(Connection cn) {
		lcTipoMov.setConexao(cn);
		lcPlanoPag.setConexao(cn);
		super.execShow(cn);
		lcCampos.carregaDados();
	}
}