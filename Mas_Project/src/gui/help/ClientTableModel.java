package gui.help;

import model.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class ClientTableModel extends AbstractTableModel {
    private final List<Client> clients;
    private final String[] columnNames = {"Name", "LastName", "Year", "email"};

    public ClientTableModel(List<Client> clients){
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client c = clients.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getName();
            case 1 -> c.getLastName();
            case 2 -> c.getAge();
            case 3 -> c.getEmail();
            default -> null;
        };
    }

}
