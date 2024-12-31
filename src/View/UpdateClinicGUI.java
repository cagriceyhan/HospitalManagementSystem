package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UpdateClinicGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Clinic clinic;
	private JTextField fld_clinicName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_2 = new JLabel("Poliklinik Adı");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(10, 10, 100, 29);
		contentPane.add(label_2);

		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_updateClinic.setBounds(10, 78, 191, 29);
		contentPane.add(btn_updateClinic);

		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fld_clinicName.setBounds(10, 39, 178, 29);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		fld_clinicName.setColumns(10);
	}

}
