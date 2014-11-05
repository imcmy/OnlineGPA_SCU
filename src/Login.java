import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Login {

	private JFrame frmOnlinegpaVer;
	private JTextField textField;
	private JPasswordField passwordField;
	private OnlineGPA ok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmOnlinegpaVer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmOnlinegpaVer = new JFrame();
		frmOnlinegpaVer.setBackground(SystemColor.textHighlight);
		frmOnlinegpaVer.setTitle("OnlineGPA Ver0.05");
		frmOnlinegpaVer.setBounds(100, 100, 333, 249);
		frmOnlinegpaVer.setMinimumSize(new Dimension(333,249));
		frmOnlinegpaVer.setMaximumSize(new Dimension(333,249));
		frmOnlinegpaVer.setPreferredSize(new Dimension(333,249));
		frmOnlinegpaVer.setResizable(false);
		frmOnlinegpaVer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOnlinegpaVer.setLocationRelativeTo(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlight);
		frmOnlinegpaVer.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);

		JLabel label = new JLabel("\u7528 \u6237 \u540D");
		label.setForeground(new Color(255, 255, 0));
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(10, 48, 56, 15);
		panel_2.add(label);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				if (textField.getText().length() > 12 && k.getKeyChar() != '\b') {
					k.setKeyChar('\0');
				}
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(74, 45, 233, 21);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setForeground(new Color(255, 255, 0));
		label_1.setFont(new Font("黑体", Font.PLAIN, 14));
		label_1.setBounds(10, 81, 56, 15);
		panel_2.add(label_1);

		JButton button = new JButton("\u5F00\u59CB\u5427\uFF01");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zjh = textField.getText();
				String mm = String.valueOf(passwordField.getPassword());

				if (zjh.equals("") || mm.equals("")) {

					JOptionPane.showMessageDialog(null, "错误用户名或密码，请重新输入!", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}

				ok = new OnlineGPA(zjh, mm);
				if (ok.getFinal().equals("Error!")) {

					JOptionPane.showMessageDialog(null, "错误用户名或密码，请重新输入!", "提示", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
					return;
				} else {

					frmOnlinegpaVer.setVisible(false);
					GradeWindow g = new GradeWindow(ok);
				}
			}
		});
		button.setBounds(10, 123, 130, 23);
		panel_2.add(button);

		JButton button_1 = new JButton("\u91CD\u7F6E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		button_1.setBounds(177, 123, 130, 23);
		panel_2.add(button_1);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(74, 78, 233, 21);
		panel_2.add(passwordField);

		JLabel lblbug = new JLabel("\u6DFB\u52A0\u6B22\u8FCE\u6D88\u606F\uFF0C\u6DFB\u52A0\u6392\u5E8F\u3001\u7B5B\u9009\u529F\u80FD\uFF0C\u6B22\u8FCE\u53CD\u6620bug");
		lblbug.setForeground(new Color(255, 255, 0));
		lblbug.setBackground(SystemColor.textHighlight);
		lblbug.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblbug.setHorizontalAlignment(SwingConstants.CENTER);
		lblbug.setBounds(10, 10, 307, 15);
		panel_2.add(lblbug);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		frmOnlinegpaVer.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblOnlinegpaVer = new JLabel("OnlineGPA \u5BD2\u5047\u7EC8\u7ED3\u7248");
		lblOnlinegpaVer.setForeground(new Color(255, 255, 0));
		lblOnlinegpaVer.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel.add(lblOnlinegpaVer);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		frmOnlinegpaVer.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JLabel lblCopyright = new JLabel("Copyright \u00A9 2013-2014 My");
		lblCopyright.setForeground(new Color(255, 255, 0));
		panel_1.add(lblCopyright);
		lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
