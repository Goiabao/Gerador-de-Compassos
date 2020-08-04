//Eric Clauter - ericclauter@gmail.com [subject: Conservatorio]
package conservatorio;

import javax.swing.JOptionPane;

public class ClassePrincipal {
    
    static String mensagemInicial = "Don't forget install LILYPOND on your device!";
    
    public static void main(String[] args) {
        //JOptionPane.showMessageDialog(null, mensagemInicial, "", 1);
        
        JanelaPrincipal principal = new JanelaPrincipal();
        principal.setVisible(true);
    }
    
}
