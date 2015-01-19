package com.liquidbol.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Franco
 */
public class ItemForm extends JFrame {

    private JPanel contentPane;
    private JLabel title;
    private JLabel idShower;
    private JLabel capLbl;
    private Component capBox;
    private JComboBox unitCB;
    private JLabel descLbl;
    private JTextField itemDesc;
    private JLabel brandLbl;
    private Component itemBrand;
    private JLabel madeLbl;
    private Component itemMade;
    private JLabel typeLbl;
    private Component itemType;
    private JLabel subtypeLbl;
    private Component itemSubtype;
    private JLabel priceLbl;
    private Component itemPrice;
    private JLabel costLbl;
    private JTextField itemCost;
    private JLabel itemPhoto;
    private JButton submitBtn;
    private MouseListener ml;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ItemForm(0).setVisible(true);
            }
        });
    }

    public ItemForm(int state) {
        switch (state) {
            case 1: //Add new client
                setStyle();
                initComponents();
                break;
            case 2: //show client data
                setStyle();
                initComponents();
                //convertToReadOnly();
                break;
            case 3: //edit client data
                setStyle();
                initComponents();
                break;
            default:
                setStyle();
                initComponents();
                break;
        }
    }

    private void initComponents() {
        setTitle("Liquid");
        setSize(550, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        title = new JLabel();
        title.setText("NUEVO ARTICULO");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        idShower = new JLabel("Nº 000001");
        idShower.setFont(new Font("Courier New", Font.PLAIN, 20));
        capLbl = new JLabel("Capacidad");
        capBox = new JTextField();
        unitCB = new JComboBox();
        descLbl = new JLabel("Descripcion");
        itemDesc = new JTextField();
        brandLbl = new JLabel("Marca");
        itemBrand = new JTextField();
        madeLbl = new JLabel("Industria");
        itemMade = new JTextField();
        typeLbl = new JLabel("Tipo");
        itemType = new JTextField();
        subtypeLbl = new JLabel("Subtipo");
        itemSubtype = new JTextField();
        costLbl = new JLabel("Costo");
        itemCost = new JTextField();
        priceLbl = new JLabel("Precio");
        itemPrice = new JTextField();

        try {
            itemPhoto = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource("/com/liquidbol/images/upload.png"))));
            itemPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(ItemForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        submitBtn = new JButton("Add");

        title.setBounds(100, 30, 400, 30);
        idShower.setBounds(350, 80, 150, 30);
        capLbl.setBounds(80, 120, 70, 30);
        capBox.setBounds(150, 120, 50, 30);
        unitCB.setBounds(200, 120, 50, 30);
        descLbl.setBounds(40, 160, 70, 30);
        itemDesc.setBounds(110, 160, 400, 30);
        brandLbl.setBounds(40, 200, 40, 30);
        itemBrand.setBounds(80, 200, 180, 30);
        madeLbl.setBounds(285, 200, 50, 30);
        itemMade.setBounds(340, 200, 170, 30);
        typeLbl.setBounds(50, 240, 70, 30);
        itemType.setBounds(80, 240, 150, 30);
        subtypeLbl.setBounds(270, 240, 70, 30);
        itemSubtype.setBounds(320, 240, 150, 30);
        costLbl.setBounds(290, 300, 40, 30);
        itemCost.setBounds(330, 300, 100, 30);
        priceLbl.setBounds(290, 340, 40, 30);
        itemPrice.setBounds(330, 340, 100, 30);
        itemPhoto.setBounds(80, 300, 150, 150);
        submitBtn.setBounds(350, 400, 100, 30);

        contentPane.add(title);
        contentPane.add(idShower);
        contentPane.add(capLbl);
        contentPane.add(capBox);
        contentPane.add(unitCB);
        contentPane.add(descLbl);
        contentPane.add(itemDesc);
        contentPane.add(brandLbl);
        contentPane.add(itemBrand);
        contentPane.add(madeLbl);
        contentPane.add(itemMade);
        contentPane.add(typeLbl);
        contentPane.add(itemType);
        contentPane.add(subtypeLbl);
        contentPane.add(itemSubtype);
        contentPane.add(costLbl);
        contentPane.add(itemCost);
        contentPane.add(priceLbl);
        contentPane.add(itemPrice);
        contentPane.add(itemPhoto);
        contentPane.add(submitBtn);
        onMouseHover(itemPhoto);
    }

    private void setStyle() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onMouseHover(JLabel lbl) {
        ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((Component) e.getSource()).removeMouseListener(this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));
                    fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
                    fc.showOpenDialog(fc);

                    File selectedIMG = fc.getSelectedFile();
                    if (selectedIMG != null) {
                        BufferedImage img = ImageIO.read(selectedIMG);
                        Image dimg = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
                        lbl.setIcon(new ImageIcon(dimg));
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex + ""
                            + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl.setEnabled(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl.setEnabled(true);
            }
        };
        lbl.addMouseListener(ml);
    }

    /*   private void convertToReadOnly() {
     Icon temp = itemPhoto.getIcon();
     contentPane.remove(capBox);
     contentPane.remove(clientName);
     contentPane.remove(itemBrand);
     contentPane.remove(itemMade);
     contentPane.remove(itemType);
     contentPane.remove(itemSubtype);
     contentPane.remove(itemPrice);
     contentPane.remove(itemPhoto);
     contentPane.remove(companyPhoto);
     contentPane.remove(submitBtn);
     brandLbl.setEnabled(false);

     capBox = new JLabel();
     clientName = new JLabel();
     itemBrand = new JLabel();
     itemMade = new JLabel();
     itemType = new JLabel();
     itemSubtype = new JLabel();
     itemPrice = new JLabel();
     itemPhoto = new JLabel(temp);
     companyPhoto = new JLabel(temp2);
     JButton cxc = new JButton("CXC");
     JButton ar = new JButton("Art. Recargables");
     title.setText("VER ARTICULO"); //CHANGE!!!!

     capBox.setFont(new Font("Arial", Font.PLAIN, 20));
     clientName.setFont(new Font("Arial", Font.PLAIN, 20));
     itemBrand.setFont(new Font("Arial", Font.PLAIN, 20));
     itemMade.setFont(new Font("Arial", Font.PLAIN, 20));
     itemType.setFont(new Font("Arial", Font.PLAIN, 20));
     itemSubtype.setFont(new Font("Arial", Font.PLAIN, 20));
     itemPrice.setFont(new Font("Arial", Font.PLAIN, 20));

     capBox.setBounds(120, 80, 100, 30);
     clientName.setBounds(100, 120, 350, 30);
     itemBrand.setBounds(130, 160, 300, 30);
     itemMade.setBounds(100, 210, 350, 30);
     itemType.setBounds(100, 250, 150, 30);
     itemSubtype.setBounds(330, 250, 150, 30);
     itemPrice.setBounds(100, 290, 250, 30);
     itemPhoto.setBounds(75, 330, 100, 100);
     companyPhoto.setBounds(200, 330, 150, 100);
     cxc.setBounds(380, 350, 120, 30);
     ar.setBounds(380, 390, 120, 30);

     contentPane.add(capBox);
     contentPane.add(clientName);
     contentPane.add(itemBrand);
     contentPane.add(itemMade);
     contentPane.add(itemType);
     contentPane.add(itemSubtype);
     contentPane.add(itemPrice);
     contentPane.add(itemPhoto);
     contentPane.add(companyPhoto);
     contentPane.add(cxc);
     contentPane.add(ar);
     } */
}