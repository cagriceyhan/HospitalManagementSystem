package View;


import java.awt.EventQueue;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;


public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JPasswordField fld_hastaPass;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 128, 128));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("photo.png")));
		lbl_logo.setBounds(300, 10, 183, 169);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLAbel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz!");
		lblNewLAbel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLAbel.setBounds(198, 174, 425, 55);
		w_pane.add(lblNewLAbel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(new Color(255, 0, 0));
		w_tabpane.setBounds(10, 224, 766, 329);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 0, 0));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel TCKimlikLabel = new JLabel("T.C. Kimlik No:");
		TCKimlikLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		TCKimlikLabel.setBounds(10, 10, 158, 55);
		w_hastaLogin.add(TCKimlikLabel);
		
		JLabel SifreLabel = new JLabel("Şifre:");
		SifreLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SifreLabel.setBounds(10, 62, 66, 55);
		w_hastaLogin.add(SifreLabel);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(178, 29, 226, 29);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(179, 76, 225, 29);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}	
							}          
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadı lütfen kayıt olunuz");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_hastaLogin.setBounds(433, 29, 225, 29);
		w_hastaLogin.add(btn_hastaLogin);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_register.setBounds(433, 77, 225, 26);
		w_hastaLogin.add(btn_register);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 0, 0));
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel label = new JLabel("T.C. Kimlik No:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(10, 10, 158, 55);
		w_doctorLogin.add(label);
		
		JLabel label_1 = new JLabel("Şifre:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_1.setBounds(10, 62, 66, 55);
		w_doctorLogin.add(label_1);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(179, 29, 225, 29);
		w_doctorLogin.add(fld_doctorTc);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(179, 76, 225, 29);
		w_doctorLogin.add(fld_doctorPass);
		
		JButton btn_doctorLogin = new JButton("Giriş Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}          
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btn_doctorLogin.setBounds(433, 29, 225, 76);
		w_doctorLogin.add(btn_doctorLogin);
	}
}
