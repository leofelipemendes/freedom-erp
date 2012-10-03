/**
 * @version 25/06/2004 <BR>
 * @author Setpoint Inform�tica Ltda./Anderson Sanchez <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.cfg <BR>
 * Classe:
 * @(#)FPais.java <BR>
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
 * Coment�rios sobre a classe...
 * 
 */

package org.freedom.modulos.cfg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.freedom.componentes.ImprimeOS;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.FDados;

public class FPais extends FDados implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextFieldPad txtCodPais = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtNomePais = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldPad txtSiglaPais = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private JTextFieldPad txtDDIPais = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	public FPais() {

		super();
		setTitulo( "Cadastro de Paises" );
		setAtribos( 50, 50, 410, 125 );

		lcCampos.setUsaME( false );

		adicCampo( txtCodPais, 7, 20, 70, 20, "CodPais", "C�d.pais", ListaCampos.DB_PK, true );
		adicCampo( txtNomePais, 80, 20, 177, 20, "NomePais", "Nome do pais", ListaCampos.DB_SI, true );
		adicCampo( txtSiglaPais, 260, 20, 77, 20, "SiglaPais", "Sigla", ListaCampos.DB_SI, true );
		adicCampo( txtDDIPais, 340, 20, 40, 20, "DDIPais", "DDI", ListaCampos.DB_SI, false );
		setListaCampos( true, "PAIS", "SG" );
		
		btImp.addActionListener( this );
		btPrevimp.addActionListener( this );
		lcCampos.setQueryInsert( false );
		
		setImprimir( true );
		
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btPrevimp ) {
			imprimir( true );
		}
		else if ( evt.getSource() == btImp ) {
			imprimir( false );
		}
		
		super.actionPerformed( evt );
		
	}

	private void imprimir( boolean bVisualizar ) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ImprimeOS imp = new ImprimeOS( "", con );	
		int linPag = imp.verifLinPag() - 1;	
		
		try {
		
			imp.limpaPags();
			imp.montaCab();
			imp.setTitulo( "Relat�rio de paises cadastrados" );
			
			String sSQL = "SELECT CODPAIS,NOMEPAIS,SIGLAPAIS,DDIPAIS FROM SGPAIS ORDER BY NOMEPAIS";
			
			ps = con.prepareStatement( sSQL );
			rs = ps.executeQuery();
			
			while ( rs.next() ) {
				
				if ( imp.pRow() >= linPag ) {
					imp.incPags();
					imp.eject();
				}
				
				if ( imp.pRow() == 0 ) {
					
					imp.impCab( 136, false );
					
					imp.say( 0, imp.normal() );					
					imp.say( 2, "C�d.pais" );
					imp.say( 15, "Nome" );
					imp.say( 80, "Sigla" );
					imp.say( 100, "DDI" );
					
					imp.pulaLinha( 1, imp.normal() );
					imp.say( 0, Funcoes.replicate( "-", 135 ) );
					
				}
				
				imp.pulaLinha( 1, imp.normal() );
				imp.say( 2, rs.getString( "CodPais" ) != null ? rs.getString( "CodPais" ) : ""  );
				imp.say( 15, rs.getString( "NomePais" ) != null ? rs.getString( "NomePais" ) : ""  );
				imp.say( 80, rs.getString( "SiglaPais" ) != null ? rs.getString( "SiglaPais" ) : ""  );
				imp.say( 100, rs.getString( "DDIPais" ) != null ? rs.getString( "DDIPais" ) : "" );
				
				
			}

			imp.pulaLinha( 1, imp.normal() );
			imp.say( imp.pRow() + 0, 0, Funcoes.replicate( "=", 135 ) );
			
			imp.eject();
			imp.fechaGravacao();

			rs.close();
			ps.close();
			
			if ( !con.getAutoCommit() ) {
				con.commit();
			}
			
		} catch ( SQLException err ) {
			err.printStackTrace();
			Funcoes.mensagemErro( this, "Erro consulta tabela de paises!" + err.getMessage(), true, con, err );
		}

		if ( bVisualizar ) {
			imp.preview( this );
		}
		else {
			imp.print();
		}
		
	}
	
}