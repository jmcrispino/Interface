import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class VentanaConfiguracion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldIp;
	private JTextField textFieldPuerto;
	private ArchivoDePropiedades adp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaConfiguracion dialog = new VentanaConfiguracion(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfiguracion(JFrame ventanaPadre) {
		adp = new ArchivoDePropiedades("config.properties");
		
		setTitle("Configurar IP + Puerto");
		setAlwaysOnTop(true);
		setModal(true);
		
		setBounds(100, 100, 319, 100);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblIp = new JLabel("IP");
			contentPanel.add(lblIp);
		}
		{
			textFieldIp = new JTextField();
			contentPanel.add(textFieldIp);
			textFieldIp.setColumns(10);
		}
		{
			JLabel lblPuerto = new JLabel("Puerto");
			contentPanel.add(lblPuerto);
		}
		{
			textFieldPuerto = new JTextField();
			textFieldPuerto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char caracter = arg0.getKeyChar();
					if(caracter < '0' || caracter > '9') {
						arg0.consume();
					}
						
				}
			});
			contentPanel.add(textFieldPuerto);
			textFieldPuerto.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textFieldIp.getText().length() > 0 && textFieldPuerto.getText().length() > 0)
							adp.escritura(textFieldIp.getText(), Integer.parseInt(textFieldPuerto.getText()));
						dispose();	
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		adp.lectura();
		textFieldIp.setText(adp.getIP());
		textFieldPuerto.setText("" + adp.getPuerto());
		setLocationRelativeTo(ventanaPadre);
		setVisible(true);
		textFieldIp.requestFocus();
		textFieldIp.selectAll();		
	}

}
