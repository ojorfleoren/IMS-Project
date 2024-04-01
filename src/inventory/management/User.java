package inventory.management;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author OJT-21
 */
public class User extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    public User() {
        initComponents();
        conn = LogIn.connectDB();
        setExtendedState(User.MAXIMIZED_BOTH);
        
        // Call the method and display the count of each area
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        alignText(tblStock);
        alignRelease(tblRelease);
        alignChecking(tblChecking);
        alignReturn(tblReturn);
        alignRepair(tblRepair);
        alignDisposal(tblDisposal);
        //Alignment
 
        //End----------------------------------------------------------------End
    }
    private static final int[] stockAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] minWidths = {15, 40, 150, 120, 200, 50, 70, 15};
    //
    private static final int[] alignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] Widths = {60, 15, 80, 130, 100, 170, 60, 90, 15, 90};
    
    
    public static void alignText(JTable tblStock) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblStock.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblStock.getColumnCount() && i < stockAlignment.length; i++) {
            int align = stockAlignment[i];
            int minWidth = minWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblStock.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblStock.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell
           
            // Set minimum width for header
            tblStock.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignRelease(JTable tblRelease) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRelease.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblRelease.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRelease.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRelease.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
           
            // Set minimum width for header
            tblRelease.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignChecking(JTable tblChecking) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblChecking.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblChecking.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblChecking.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblChecking.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblChecking.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignReturn(JTable tblReturn) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblReturn.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblReturn.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblReturn.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblReturn.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblReturn.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignRepair(JTable tblRepair) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRepair.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblRepair.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRepair.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRepair.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblRepair.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    public static void alignDisposal(JTable tblDisposal) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDisposal.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        
        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < tblDisposal.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int Width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDisposal.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDisposal.getColumnModel().getColumn(i).setMinWidth(Width); // Set minimum width for cell
            tblDisposal.getTableHeader().getColumnModel().getColumn(i).setMinWidth(Width);
        }
    }
    //End-------------------------------------------------------------------------------------------------------------End
//Functionalities Method    
//Display the quantity of each area into the dashboard    
public void displayTotalDataCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Stock";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblTotalqty.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReleaseCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Release";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRelease.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayCheckingCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Checking";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblChecking.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayRepairCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Repair";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRepair.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReturnCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Return";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblReturn.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayDisposalCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Disposal";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblDisposal.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
//End------------------------------------------------------------------------End
//Display the information of each area in the table
public void displayStockItems() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            int serialNumber = rst.getInt("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
public void displayReleaseData() {
    DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
    releaseModel.setRowCount(0); // Clear existing rows
    
    try {
        String sql = "SELECT r.ReleaseID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS ReleasedQty, r.Date " +
                     "FROM Release r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Release data from the database.");
    }
}
public void displayCheckingData() {
    DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
    checkingModel.setRowCount(0); // Clear existing rows
    try {
        String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS TransferredQty, c.Date " +
                     "FROM Checking c " +
                     "LEFT JOIN Stock s ON c.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int transferredQty = rst.getInt("TransferredQty");
                Date date = rst.getDate("Date");

                // Add a row to the Checking table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, model, specification, category, brand, transferredQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Checking data from the database.");
    }
}
public void displayReturn() {
    DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
    returnModel.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification,  s.Category, s.Brand, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnedQty = rst.getInt("ReturnedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Return table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Return data from the database.");
    }
}
public void displayRepair() {
    DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
    repairModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Repair area
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS RepairedQty, rep.Date " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairedQty = rst.getInt("RepairedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Repair table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Repair data from the database.");
    }
}
public void displayDisposal() {
    DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
    disposalModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposedQty, d.Date " +
                     "FROM Disposal d " +
                     "LEFT JOIN Stock s ON d.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                int serialNo = rst.getInt("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposedQty = rst.getInt("DisposedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
//End------------------------------------------------------------------------End

public static void printStock(JTable tblStock) {
        // Get the table model
        TableModel model = tblStock.getModel();

        try {
            // Print the table
            boolean complete = tblStock.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRelease(JTable tblRelease) {
        // Get the table model
        TableModel model = tblRelease.getModel();

        try {
            // Print the table
            boolean complete = tblRelease.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printChecking(JTable tblChecking) {
        // Get the table model
        TableModel model = tblChecking.getModel();

        try {
            // Print the table
            boolean complete = tblChecking.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printReturn(JTable tblReturn) {
        // Get the table model
        TableModel model = tblReturn.getModel();

        try {
            // Print the table
            boolean complete = tblReturn.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRepair(JTable tblRepair) {
        // Get the table model
        TableModel model = tblRepair.getModel();

        try {
            // Print the table
            boolean complete = tblRepair.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printDisposal(JTable tblDisposal) {
        // Get the table model
        TableModel model = tblDisposal.getModel();

        try {
            // Print the table
            boolean complete = tblDisposal.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabUser = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblTotalqty = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblChecking = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRepair = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblReturn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblDisposal = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lblRelease = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tabstocksUser = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtSearchItem = new javax.swing.JTextField();
        btnViewUser4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRelease = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dashboard.png"))); // NOI18N
        jButton1.setText(" Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 220, 60));

        jButton2.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Records.png"))); // NOI18N
        jButton2.setText(" Records");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 220, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logo_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 247, 217));
        jLabel2.setText("''Outsourcing inspired by Quality''");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(692, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 232, 163));
        jPanel11.setPreferredSize(new java.awt.Dimension(231, 156));

        lblTotalqty.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblTotalqty.setText("0");

        jLabel28.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Total");

        jLabel29.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Stock Items");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(211, 109, 109));
        jPanel12.setPreferredSize(new java.awt.Dimension(241, 104));

        lblChecking.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblChecking.setText("0");

        jLabel31.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel31.setText("Checking Items");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Checking.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(lblChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lblChecking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18))))
        );

        jPanel14.setBackground(new java.awt.Color(106, 177, 135));
        jPanel14.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRepair.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblRepair.setText("0");

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel33.setText("Repair Items");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/repair...png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel46)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblRepair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)))
                .addGap(18, 18, 18))
        );

        jPanel21.setBackground(new java.awt.Color(102, 102, 154));
        jPanel21.setPreferredSize(new java.awt.Dimension(241, 104));

        lblReturn.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblReturn.setText("0");

        jLabel37.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel37.setText("Return Items");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Return.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37)
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(lblReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)
                        .addGap(28, 28, 28))))
        );

        jPanel20.setBackground(new java.awt.Color(190, 159, 191));
        jPanel20.setPreferredSize(new java.awt.Dimension(241, 104));

        lblDisposal.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblDisposal.setText("0");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel39.setText("Disposal Items");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Disposal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(lblDisposal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel39)
                        .addGap(28, 28, 28))))
        );

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel47.setText("DASHBOARD");

        jPanel28.setBackground(new java.awt.Color(251, 202, 126));

        lblRelease.setFont(new java.awt.Font("Rockwell", 1, 40)); // NOI18N
        lblRelease.setText("0");

        jLabel38.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel38.setText("Release Items");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/release.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRelease)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel47))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 82, Short.MAX_VALUE))
        );

        tabUser.addTab("tab1", jPanel3);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 247, 217));
        jLabel3.setText("   ''Outsourcing inspired by Quality''");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabstocksUser.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "Name ", "Model", "Specification", "Category", "Brand", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStock);
        if (tblStock.getColumnModel().getColumnCount() > 0) {
            tblStock.getColumnModel().getColumn(0).setResizable(false);
            tblStock.getColumnModel().getColumn(1).setResizable(false);
            tblStock.getColumnModel().getColumn(2).setResizable(false);
            tblStock.getColumnModel().getColumn(3).setResizable(false);
            tblStock.getColumnModel().getColumn(4).setResizable(false);
            tblStock.getColumnModel().getColumn(5).setResizable(false);
            tblStock.getColumnModel().getColumn(6).setResizable(false);
            tblStock.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel25.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel25.setText("Search:");

        txtSearchItem.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchItemActionPerformed(evt);
            }
        });

        btnViewUser4.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser4ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(93, 190, 163));
        jButton14.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton14.setText("Print");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(jButton14))
                    .addComponent(btnViewUser4))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabstocksUser.addTab("tab1", jPanel6);

        tblRelease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ReleaseID", "ItemID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty", "ReleaseDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblRelease);
        if (tblRelease.getColumnModel().getColumnCount() > 0) {
            tblRelease.getColumnModel().getColumn(0).setResizable(false);
            tblRelease.getColumnModel().getColumn(1).setResizable(false);
            tblRelease.getColumnModel().getColumn(2).setResizable(false);
            tblRelease.getColumnModel().getColumn(3).setResizable(false);
            tblRelease.getColumnModel().getColumn(4).setResizable(false);
            tblRelease.getColumnModel().getColumn(5).setResizable(false);
            tblRelease.getColumnModel().getColumn(6).setResizable(false);
            tblRelease.getColumnModel().getColumn(7).setResizable(false);
            tblRelease.getColumnModel().getColumn(8).setResizable(false);
            tblRelease.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton15.setBackground(new java.awt.Color(93, 190, 163));
        jButton15.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton15.setText("Print");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15)
                .addContainerGap(900, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jButton15)
                .addGap(2, 2, 2))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        tabstocksUser.addTab("tab6", jPanel13);

        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial", "ItemName", "Model", "Specifiation", "Category", "Brand", "Qty.", "CheckingDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblChecking);
        if (tblChecking.getColumnModel().getColumnCount() > 0) {
            tblChecking.getColumnModel().getColumn(0).setResizable(false);
            tblChecking.getColumnModel().getColumn(1).setResizable(false);
            tblChecking.getColumnModel().getColumn(2).setResizable(false);
            tblChecking.getColumnModel().getColumn(3).setResizable(false);
            tblChecking.getColumnModel().getColumn(4).setResizable(false);
            tblChecking.getColumnModel().getColumn(5).setResizable(false);
            tblChecking.getColumnModel().getColumn(6).setResizable(false);
            tblChecking.getColumnModel().getColumn(7).setResizable(false);
            tblChecking.getColumnModel().getColumn(8).setResizable(false);
            tblChecking.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton16.setBackground(new java.awt.Color(93, 190, 163));
        jButton16.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton16.setText("Print");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab2", jPanel7);

        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblReturn);
        if (tblReturn.getColumnModel().getColumnCount() > 0) {
            tblReturn.getColumnModel().getColumn(0).setResizable(false);
            tblReturn.getColumnModel().getColumn(1).setResizable(false);
            tblReturn.getColumnModel().getColumn(2).setResizable(false);
            tblReturn.getColumnModel().getColumn(3).setResizable(false);
            tblReturn.getColumnModel().getColumn(4).setResizable(false);
            tblReturn.getColumnModel().getColumn(5).setResizable(false);
            tblReturn.getColumnModel().getColumn(6).setResizable(false);
            tblReturn.getColumnModel().getColumn(7).setResizable(false);
            tblReturn.getColumnModel().getColumn(8).setResizable(false);
            tblReturn.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton17.setBackground(new java.awt.Color(93, 190, 163));
        jButton17.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton17.setText("Print");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab3", jPanel8);

        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "RepairDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblRepair);
        if (tblRepair.getColumnModel().getColumnCount() > 0) {
            tblRepair.getColumnModel().getColumn(0).setResizable(false);
            tblRepair.getColumnModel().getColumn(1).setResizable(false);
            tblRepair.getColumnModel().getColumn(2).setResizable(false);
            tblRepair.getColumnModel().getColumn(3).setResizable(false);
            tblRepair.getColumnModel().getColumn(4).setResizable(false);
            tblRepair.getColumnModel().getColumn(5).setResizable(false);
            tblRepair.getColumnModel().getColumn(6).setResizable(false);
            tblRepair.getColumnModel().getColumn(7).setResizable(false);
            tblRepair.getColumnModel().getColumn(8).setResizable(false);
            tblRepair.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton18.setBackground(new java.awt.Color(93, 190, 163));
        jButton18.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton18.setText("Print");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab4", jPanel9);

        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID  ", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "DisposalDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblDisposal);
        if (tblDisposal.getColumnModel().getColumnCount() > 0) {
            tblDisposal.getColumnModel().getColumn(0).setResizable(false);
            tblDisposal.getColumnModel().getColumn(1).setResizable(false);
            tblDisposal.getColumnModel().getColumn(2).setResizable(false);
            tblDisposal.getColumnModel().getColumn(3).setResizable(false);
            tblDisposal.getColumnModel().getColumn(4).setResizable(false);
            tblDisposal.getColumnModel().getColumn(5).setResizable(false);
            tblDisposal.getColumnModel().getColumn(6).setResizable(false);
            tblDisposal.getColumnModel().getColumn(7).setResizable(false);
            tblDisposal.getColumnModel().getColumn(8).setResizable(false);
            tblDisposal.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton19.setBackground(new java.awt.Color(93, 190, 163));
        jButton19.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton19.setText("Print");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton19)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabstocksUser.addTab("tab5", jPanel10);

        btnStock.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnStock.setText("Stock");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton9.setText("Checking");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton10.setText("Return");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton11.setText("Repair");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton12.setText("Disposal");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jButton13.setText("Release");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabstocksUser)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addGap(2, 2, 2)
                        .addComponent(jButton10)
                        .addGap(2, 2, 2)
                        .addComponent(jButton11)
                        .addGap(2, 2, 2)
                        .addComponent(jButton12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStock)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(2, 2, 2)
                .addComponent(tabstocksUser, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabUser.addTab("tab2", jPanel2);

        jPanel1.add(tabUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, -30, 990, 700));

        jButton3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jButton3.setText("LOG OUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 616, 180, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tabUser.setSelectedIndex(0);
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchItemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchItem.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchItemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Logout Successful");
             new LogIn().setVisible(true);
            dispose();
        } else {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tabUser.setSelectedIndex(1);
        displayStockItems();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(2);
        displayCheckingData();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(3);
        displayReturn();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(4);
        displayRepair();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(5);
        displayDisposal();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnViewUser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser4ActionPerformed
        // TODO add your handling code here:
        txtSearchItem.setText("");
        displayStockItems();
    }//GEN-LAST:event_btnViewUser4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(1);
        displayReleaseData();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        printStock(tblStock);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        printRelease(tblRelease);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        printChecking(tblChecking);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        printReturn(tblReturn);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        printRepair(tblRepair);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        printDisposal(tblDisposal);
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnViewUser4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblChecking;
    private javax.swing.JLabel lblDisposal;
    private javax.swing.JLabel lblRelease;
    private javax.swing.JLabel lblRepair;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JTabbedPane tabUser;
    private javax.swing.JTabbedPane tabstocksUser;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRelease;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtSearchItem;
    // End of variables declaration//GEN-END:variables
}
