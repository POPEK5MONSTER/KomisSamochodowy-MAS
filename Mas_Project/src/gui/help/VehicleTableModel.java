package gui.help;

import model.Vehicle;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class VehicleTableModel extends AbstractTableModel{
    private final List<Vehicle> vehicles;

    private final String[] columnNames = {"Car/MotorBike","Brand", "Model", "Year", "mileage", "Price"};

    public VehicleTableModel(List<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    @Override
    public int getRowCount() {
        return vehicles.size();
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
        Vehicle v = vehicles.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> v.getClass().getSimpleName();
            case 1 -> v.getBrand();
            case 2 -> v.getModel();
            case 3 -> v.getYearOfProduction();
            case 4 -> v.getMileage();
            case 5 -> v.calculatePrice();
            default -> null;
        };
    }
}
