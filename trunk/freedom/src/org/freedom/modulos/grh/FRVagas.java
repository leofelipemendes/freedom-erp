/**
 * @version 12/08/2008 <BR>
 * @author Setpoint Inform�tica Ltda.
 * @author Reginaldo Garcia Heua <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.grh <BR>
 * Classe:
 * @(#)FRVagas.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Tela para cadastro de estruturas de produtos.
 * 
 */
package org.freedom.modulos.grh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperPrintManager;

import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JLabelPad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FPrinterJob;
import org.freedom.telas.FRelatorio;

public class FRVagas extends FRelatorio{

	private static final long serialVersionUID = 1L;
	
	private JTextFieldPad txtCodEmpr = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0  );
	
	private JTextFieldFK txtNomeEmpr = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0  );
	
	private JTextFieldPad txtCodFuncao = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0  );
	
	private JTextFieldFK txtDescFuncao = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0  );
	
	private JTextFieldPad txtDataini = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );
	
	private JTextFieldPad txtDatafim = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );
	
	private final ListaCampos lcFuncao = new ListaCampos( this );
	
	private final ListaCampos lcEmpr = new ListaCampos( this );
	
	public FRVagas(){
		
		super( false );
		setTitulo( "Lista de Vagas" );
		setAtribos( 50, 50, 350, 220 );
	
		montaListaCampos();
		montaTela();
			
	}
	
	private void montaTela(){

		
		JLabelPad lbLinha = new JLabelPad();
		lbLinha.setBorder(BorderFactory.createEtchedBorder());
		JLabelPad lbPeriodo = new JLabelPad( "Per�odo:" , SwingConstants.CENTER );
		lbPeriodo.setOpaque( true );
		
		adic( lbPeriodo,7, 1, 80, 20 );
		adic( lbLinha,5, 10, 300, 45 );
		
		adic( new JLabelPad("De:"), 10, 25, 30, 20 );
		adic( txtDataini, 40, 25, 97, 20 );
		adic( new JLabelPad("At�:"),152, 25, 37, 20 );
		adic( txtDatafim, 190, 25, 100, 20 );		
		adic( new JLabelPad("C�d.Empr."), 7, 55, 70, 20 );
		adic( txtCodEmpr, 7, 75, 70, 20 );
		adic( new JLabelPad("Descri��o do Empregador"), 80, 55, 250, 20 );
		adic( txtNomeEmpr, 80, 75, 250, 20 );
		adic( new JLabelPad("C�d.Func"), 7, 95, 70, 20 );
		adic( txtCodFuncao, 7, 115, 70, 20 );
		adic( new JLabelPad("Descri��o da Fun��o"), 80, 95, 250, 20 );
		adic( txtDescFuncao, 80, 115, 250, 20 );
	}
	
	private void montaListaCampos(){
		
		/******************
		 *    Fun��o      *
		 ******************/
		lcFuncao.add( new GuardaCampo( txtCodFuncao, "CodFunc", "C�d.Func.", ListaCampos.DB_PK, false ) );
		lcFuncao.add( new GuardaCampo( txtDescFuncao, "DescFunc", "Descri��o da fun��o", ListaCampos.DB_SI, false ) );
		txtCodFuncao.setTabelaExterna( lcFuncao );
		txtCodFuncao.setNomeCampo( "CodFunc" );
		txtCodFuncao.setFK( true );
		lcFuncao.setReadOnly( true );
		lcFuncao.montaSql( false, "FUNCAO", "RH" );
		
		
	
		/******************
		 *   Empregador   *
		 ******************/
		lcEmpr.add( new GuardaCampo( txtCodEmpr, "CodEmpr", "C�d.Empr..", ListaCampos.DB_PK, false ) );
		lcEmpr.add( new GuardaCampo( txtNomeEmpr, "NomeEmpr", "Descri��o do Empregador", ListaCampos.DB_SI, false ) );
		txtCodEmpr.setTabelaExterna( lcEmpr );
		txtCodEmpr.setNomeCampo( "CodEmpr" );
		txtCodEmpr.setFK( true );
		lcEmpr.setReadOnly( true );		
		lcEmpr.montaSql( false, "EMPREGADOR", "RH" );
		
		
	}

	public void imprimir( boolean bVisualizar ) {
		
		FPrinterJob dlGr = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sWhere =  new StringBuffer();
		
		try {
			
			if( txtCodFuncao.getVlrInteger() > 0 ){
				
				sWhere.append( "AND V.CODFUNC = " + txtCodFuncao.getVlrInteger() );
			}
			if( txtCodEmpr.getVlrInteger() > 0  ){
				
				sWhere.append( "AND V.CODEMPR = " + txtCodEmpr.getVlrInteger() );
			}
			if( txtDataini.getVlrDate() != null && txtDatafim.getVlrDate() != null  ){
				
				sWhere.append( "AND V.DTINS BETWEEN ? AND ?" ); 
				
			}
			
			sql.append( "SELECT E.NOMEEMPR, F.DESCFUNC, T.DESCTURNO, V.FAIXASALINI, V.FAIXASALFIM " );
			sql.append( "FROM RHVAGA V, RHEMPREGADOR E, RHFUNCAO F, RHTURNO T " );
			sql.append( "WHERE V.CODEMP=? AND V.CODFILIAL=? AND " );
			sql.append( "V.CODEMPEM=E.CODEMP AND V.CODFILIALEM=E.CODFILIAL AND V.CODEMPR=E.CODEMPR AND " );
			sql.append( "V.CODEMPFC=F.CODEMP AND V.CODFILIALFC=F.CODFILIAL AND V.CODFUNC=F.CODFUNC AND  " );
			sql.append( "V.CODEMPTN=T.CODEMP AND V.CODFILIALTN=T.CODFILIAL AND V.CODTURNO=T.CODTURNO " );
			sql.append( sWhere.toString() );
			sql.append( "ORDER BY E.NOMEEMPR, F.DESCFUNC, T.DESCTURNO, V.FAIXASALINI, V.FAIXASALFIM " );
			
			ps = con.prepareStatement( sql.toString() );
			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "RHVAGA" ) );
			
			if( txtDataini.getVlrDate() != null && txtDatafim.getVlrDate() != null  ){
				
				ps.setDate( 3, Funcoes.dateToSQLDate( txtDataini.getVlrDate() ));
				ps.setDate( 4, Funcoes.dateToSQLDate( txtDatafim.getVlrDate() ));
			}
			rs = ps.executeQuery();
			
			
		} catch ( SQLException e ) {
			
			e.printStackTrace();
			Funcoes.mensagemErro( this, "Erro ao buscar dados! " + e.getMessage() );
		} 

		dlGr = new FPrinterJob( "relatorios/grhVagas.jasper", "Lista de Vagas", "", rs, null, this );
	 
		if ( bVisualizar ) {
			dlGr.setVisible( true );
		}
		else {
			try {
				JasperPrintManager.printReport( dlGr.getRelatorio(), true );
			} catch ( Exception e ) {
				e.printStackTrace();
				Funcoes.mensagemErro( this, "Erro na gera��o do rel�torio!" + e.getMessage(), true, con, e );
			}
		}
	}
	
	public void setConexao( Connection con ){
		
		super.setConexao( con );
		lcEmpr.setConexao( con );
		lcFuncao.setConexao( con );
	}
}
