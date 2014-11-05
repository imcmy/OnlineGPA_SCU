import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;


public class GradeWindow {

	private JFrame frmOnlinegpaVer;
	private OnlineGPA gpa;
	private JTable table;

	/*
	 * Launch the application.
	 
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeWindow window = new GradeWindow(ok);
					window.frmOnlinegpaVer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GradeWindow(OnlineGPA ok) {
		gpa = ok;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOnlinegpaVer = new JFrame();
		frmOnlinegpaVer.setTitle("OnlineGPA Ver0.05");
		frmOnlinegpaVer.setBounds(100, 100, 515, 375);
		frmOnlinegpaVer.setMinimumSize(new Dimension(515,375));
		frmOnlinegpaVer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOnlinegpaVer.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmOnlinegpaVer.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6587\u4EF6");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u590D\u5236");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				String summary = gpa.getSummary() + "\n" + gpa.getFinal();
				StringSelection stringSel = new StringSelection(summary);
				clipboard.setContents(stringSel, null);
				
				JOptionPane.showMessageDialog(null, "复制成功!", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u9000\u51FA");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u5173\u4E8E");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "版权所有 侵权我也不知道\n\nCopyright\u00A9 2013-2014 My", "提示", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		frmOnlinegpaVer.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		frmOnlinegpaVer.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		String CNO[] = gpa.getCNO();
	    String Cname[] = gpa.getCname();
	    float Cscore[] = gpa.getCscore();
	    String Cproperty[] = gpa.getCproperty();
	    float Cgrade[] = gpa.getCgrade();
	    
	    String[] header = {"课程号", "课程名", "学分", "课程属性", "分数"};
	    Object[][] content = new Object[gpa.getLength()][5];
	    for (int i=0; i<gpa.getLength(); i++) {
	    	int j = 0;
	    	content[i][j++] = CNO[i];
	    	content[i][j++] = Cname[i];
	    	content[i][j++] = Cscore[i];
	    	content[i][j++] = Cproperty[i];
	    	content[i][j++] = Cgrade[i];
	    }
		panel_1.setLayout(new BorderLayout(0, 0));
		TableModel model = new DefaultTableModel(content, header) {
		    public Class getColumnClass(int column) {
		        Class returnValue;
		        if ((column >= 0) && (column < getColumnCount())) {
		            returnValue = getValueAt(0, column).getClass();
		        } else {
		            returnValue = Object.class;
		        }
		        return returnValue;
		    }
		};
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
        table.setRowSorter(sorter);
		table.setFont(new Font("宋体", Font.PLAIN, 12));
		table.setRowSelectionAllowed(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
		//panel_1.add(table);
		TableColumn Column1 = table.getColumnModel().getColumn(0);
		Column1.setCellRenderer(render);
		Column1.setPreferredWidth(60);
		
		TableColumn Column2 = table.getColumnModel().getColumn(1);
		Column2.setPreferredWidth(170);
		
		TableColumn Column3 = table.getColumnModel().getColumn(2);
		Column3.setCellRenderer(render);
		Column3.setPreferredWidth(30);
		
		TableColumn Column4 = table.getColumnModel().getColumn(3);
		Column4.setCellRenderer(render);
		Column4.setPreferredWidth(30);
		
		TableColumn Column5 = table.getColumnModel().getColumn(4);
		Column5.setCellRenderer(render);
		Column5.setPreferredWidth(40);
				
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.textHighlight);
		panel_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		final JComboBox comboBox = new JComboBox();
		panel_4.add(comboBox, BorderLayout.CENTER);
		comboBox.addItem("");
		comboBox.addItem("必修");
		comboBox.addItem("选修");
		comboBox.addItem("任选");
		
		JLabel lblNewLabel_1 = new JLabel("   \u7B5B\u9009\u5C5E\u6027   ");
		lblNewLabel_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_1.setBackground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_1, BorderLayout.WEST);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.textHighlight);
		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel(gpa.getName());
		lblNewLabel_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_2.setBackground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.textHighlight);
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.textHighlight);
		panel_5.add(panel_7, BorderLayout.SOUTH);
		comboBox.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                int index = comboBox.getSelectedIndex();
                if (index == 0) {
                	sorter.setRowFilter(null);
                } else if (index == 1) {
                	sorter.setRowFilter(RowFilter.regexFilter("必修"));
                } else if (index == 2) {
                	sorter.setRowFilter(RowFilter.regexFilter("选修"));
                } else if (index == 3) {
                	sorter.setRowFilter(RowFilter.regexFilter("任选"));
                }
            }
        });
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		frmOnlinegpaVer.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(gpa.getFinal());
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlight);
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.textHighlight);
		panel.add(panel_3, BorderLayout.NORTH);
	}

}
