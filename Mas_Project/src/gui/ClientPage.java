package gui;

import gui.help.ClientTableModel;
import model.Client;
import util.ObjcetPlus;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

public class ClientPage extends JFrame {
    private JPanel mainPanel;
    private JTable clientTable;

    public ClientPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setTitle("Login");
        setVisible(true);

        List<Client> clients = new ArrayList<>(ObjcetPlus.getExtentFromClass(Client.class));

        ClientTableModel clientTableModel = new ClientTableModel(clients);
        clientTable.setModel(clientTableModel);
        clientTable.setTableHeader(null);

        clientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = clientTable.getSelectedRow();
                    if (row >= 0) {
                        Client selectedClient = clients.get(row);

                        SwingUtilities.invokeLater(() -> {
                            new VehiclePage(selectedClient);
                            dispose();
                        });
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    ObjcetPlus.save();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
