package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 360);
		JPanel w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 128));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(10, 10, 100, 29);
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 42, 266, 29);
		w_pane.add(fld_name);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numarası");
		lblTcNumaras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTcNumaras.setBounds(10, 81, 253, 29);
		w_pane.add(lblTcNumaras);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 120, 266, 29);
		w_pane.add(fld_tcno);
		
		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblifre.setBounds(10, 159, 253, 29);
		w_pane.add(lblifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 198, 266, 29);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() == 0 || fld_pass.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(),fld_pass.getText(),fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_register.setBounds(10, 237, 266, 29);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_backto.setBounds(10, 284, 266, 29);
		w_pane.add(btn_backto);
	}
}
