package co.edu.uptc.view;

import javax.swing.JOptionPane;

public class WindowView {
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, 
				msg, 
				"FitManager", 
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showWarningMessage(String msg) {
		JOptionPane.showMessageDialog(null, 
				msg, 
				"FitManager", 
				JOptionPane.WARNING_MESSAGE);
	}
	public void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, 
				msg, 
				"FitManager", 
				JOptionPane.ERROR_MESSAGE);
	}
	public int showFarewellWindow() {
		int opt = JOptionPane.showOptionDialog(null, 
        		"¿Esta seguro de salir de la aplicacion?", 
        		"FitManager", 	
        		JOptionPane.YES_NO_CANCEL_OPTION, 
        		JOptionPane.QUESTION_MESSAGE,
        		null,
        		new Object[]{"Si", "No", "Cancelar"},
        		"Si");
        switch (opt) {
        	case JOptionPane.YES_OPTION ->{ 
        		showMessage("Hasta pronto..."); 
        		return 7;
        		} 
        	case JOptionPane.NO_OPTION -> {
        		showMessage("Presionaste No\nRegresaremos al menu...");
        		return 1;
        		}
        	case JOptionPane.CANCEL_OPTION -> {
        		showMessage("Presionaste cancelar\nRegresaremos al menu....");
        		return 1;
        		}
        	default -> {
        		return 1;
        		}
        	}
        
    }
	public String askString(String msg) {
		return JOptionPane.showInputDialog(
		        null,
		        msg,
		        "CUENTA BANCARIA",
		        JOptionPane.QUESTION_MESSAGE
		    );
	}
	public double askDouble(String msg) {
		return Double.parseDouble(JOptionPane.showInputDialog(
		        null,
		        msg,
		        "CUENTA BANCARIA",
		        JOptionPane.QUESTION_MESSAGE
		    ));
	}
}
