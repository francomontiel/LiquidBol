package com.liquidbol.gui;

import com.liquidbol.addons.DateLabelFormatter;
import com.liquidbol.addons.UIStyle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * @author Franco
 */
public class ExtingForm extends JFrame {

    private JPanel contentPane;
    private JButton submitBtn;
    private JDatePickerImpl datePicker;
    private JLabel title;
    private JLabel idShower;
    private JLabel nameLbl;
    private JTextField clientName;
    private JTextField clientPhone;
    private JLabel phoneLbl;
    private JTable contentTable;
    private JLabel totalLbl;
    private JTextField totalAmount;
    private JLabel compLbl;
    private JTextField clientComp;
    private JLabel quantLbl;
    private JTextField quantAmount;
    private JLabel ACLbl;
    private JTextField ACAmount;
    private JLabel balanceLbl;
    private JTextField balanceAmount;
    private JLabel dateLbl;
    private JDatePickerImpl dateField;
    private JTextField hourField;
    private JLabel hourLbl;
    private JLabel amountLbl;
    private JButton backBtn;

    public ExtingForm() {
        UIStyle sty = new UIStyle();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(850, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        title = new JLabel("NOTA DE RECEPCIÓN DE EXTINTORES");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        nameLbl = new JLabel("Señor(es)");
        clientName = new JTextField();
        phoneLbl = new JLabel("Telf/Cel");
        clientPhone = new JTextField();
        compLbl = new JLabel("Empresa");
        clientComp = new JTextField();

        String[] columnNames = {"Cant.",
            "No. Extintor",
            "Descripcion/Marca",
            "Tipo",
            "Capacidad",
            "Obs",
            "Tipo Servicio"
        };

        Object[][] tempData = {
            {1, "57885", "Extintor chino c/Pico", "PQ", "3 Lb", "En periodo de garantia", "Recarga"},
            {2, "4712", "Extintor Ferton c/Manguera", "CO2", "5Kg.", "Primera vez", "Recarga"}
        };
        contentTable = new JTable(tempData, columnNames);
        contentTable.getTableHeader().setReorderingAllowed(false);
        contentTable.setFont(new Font("Arial", Font.PLAIN, 16));
        contentTable.setRowHeight(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        contentTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(20);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(170);
        contentTable.getColumnModel().getColumn(6).setPreferredWidth(60);
        contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        RowSorter<TableModel> sorter = new TableRowSorter<>(contentTable.getModel());
        contentTable.setRowSorter(sorter);
        JScrollPane tablesp = new JScrollPane(contentTable);

        quantLbl = new JLabel("Cant. Total");
        quantAmount = new JTextField();
        amountLbl = new JLabel("Monto a pagar (Bs)");
        totalLbl = new JLabel("Total");
        totalAmount = new JTextField();
        ACLbl = new JLabel("A/C");
        ACAmount = new JTextField();
        balanceLbl = new JLabel("Saldo");
        balanceAmount = new JTextField();
        dateLbl = new JLabel("Fecha de entrega");
        dateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        hourLbl = new JLabel("Hora");
        hourField = new JTextField();
        submitBtn = new JButton("OK");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Extinguisher recieved! \n Respect+");
                LoginForm.LF.setVisible(true);
                dispose();
            }
        });
        backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm.mm.setVisible(true);
                dispose();
            }
        });

        title.setBounds(140, 20, 600, 30);
        datePicker.setBounds(300, 70, 150, 30);
        idShower.setBounds(500, 70, 150, 30);
        nameLbl.setBounds(90, 110, 150, 30);
        clientName.setBounds(150, 110, 350, 30);
        compLbl.setBounds(90, 140, 130, 30);
        clientComp.setBounds(150, 140, 350, 30);
        phoneLbl.setBounds(565, 110, 150, 30);
        clientPhone.setBounds(610, 110, 150, 30);
        tablesp.setBounds(15, 180, 810, 150);
        quantLbl.setBounds(20, 330, 70, 30);
        quantAmount.setBounds(80, 330, 50, 30);
        dateLbl.setBounds(170, 325, 110, 30);
        dateField.setBounds(170, 350, 130, 30);
        hourLbl.setBounds(320, 325, 50, 30);
        hourField.setBounds(320, 350, 90, 30);
        amountLbl.setBounds(480, 350, 120, 30);
        totalLbl.setBounds(590, 330, 50, 30);
        totalAmount.setBounds(590, 350, 70, 30);
        ACLbl.setBounds(660, 330, 50, 30);
        ACAmount.setBounds(660, 350, 70, 30);
        balanceLbl.setBounds(730, 330, 50, 30);
        balanceAmount.setBounds(730, 350, 70, 30);
        submitBtn.setBounds(620, 390, 100, 30);
        backBtn.setBounds(100, 390, 100, 30);

        contentPane.add(title);
        contentPane.add(datePicker);
        contentPane.add(idShower);
        contentPane.add(nameLbl);
        contentPane.add(clientName);
        contentPane.add(compLbl);
        contentPane.add(clientComp);
        contentPane.add(phoneLbl);
        contentPane.add(clientPhone);
        contentPane.add(tablesp);
        contentPane.add(quantLbl);
        contentPane.add(quantAmount);
        contentPane.add(dateLbl);
        contentPane.add(dateField);
        contentPane.add(hourLbl);
        contentPane.add(hourField);
        contentPane.add(amountLbl);
        contentPane.add(totalLbl);
        contentPane.add(totalAmount);
        contentPane.add(ACLbl);
        contentPane.add(ACAmount);
        contentPane.add(balanceLbl);
        contentPane.add(balanceAmount);
        contentPane.add(submitBtn);
        contentPane.add(backBtn);
    }
}