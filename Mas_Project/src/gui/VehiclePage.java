package gui;

import gui.help.ReservationDialog;
import gui.help.VehicleTableModel;
import model.*;
import util.ObjcetPlus;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

public class VehiclePage extends JFrame{
    private JPanel mainPanel;
    private JTable carTable;
    private JButton buttonInfo;
    private JButton buttonReservation;
    private JButton buttonBack;

    public VehiclePage(Client client){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setTitle("Auto-Centrum");
        setVisible(true);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.addAll(ObjcetPlus.getExtentFromClass(Car.class));
        vehicles.addAll(ObjcetPlus.getExtentFromClass(Motorbike.class));

        VehicleTableModel vehicleTableModel = new VehicleTableModel(vehicles);
        carTable.setModel(vehicleTableModel);
        carTable.setTableHeader(null);



        buttonBack.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                new ClientPage();
                dispose();
            });
        });

        buttonInfo.addActionListener(e -> {
            List<Reservation> reservations = client.getReservation();
            if (reservations.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No reservation.");
            } else {
                StringBuilder history = new StringBuilder("Reservations:\n");
                for (Reservation r : reservations) {
                    history.append(r).append("\n");
                }
                JOptionPane.showMessageDialog(this, history);
            }
        });

        buttonReservation.addActionListener(e -> {
            int row = carTable.getSelectedRow();
            if (row >= 0) {
                Vehicle selectedVehicle = vehicles.get(row);
                ReservationDialog dialog = new ReservationDialog(this);
                dialog.setVisible(true);

                if (dialog.isConfirmed()) {
                    try {
                        Reservation reservation = new Reservation(
                                selectedVehicle,
                                client,
                                dialog.getFromDate(),
                                dialog.getToDate()
                        );
                        JOptionPane.showMessageDialog(this, "Reservation added:\n" + reservation);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No selected cars.");
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
