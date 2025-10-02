import gui.ClientPage;
import model.*;
import util.ObjcetPlus;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.EnumSet;


public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("12345678901234567", "WPNMC20", "NISSAN", "350z", 2003, 20000, new Sale(false), 5, EnumSet.of(Type.SPORT));
        Motorbike motorbike1 = new Motorbike("12345678901234567", "WPNMC20", "NISSAN", "350z", 2003, 20000, new Sale(false), 5);
        Client client1 = new Client("Mateusz", "Popowski", 2003, new Address("Maczka", "Plonsk"), "mp@gmail.com", "123456789");
        Client client2 = new Client("Kamil", "Popowski", 2003, new Address("Maczka", "Plonsk"), "mp@gmail.com", "123456789");

        car1.addReservation(client1, LocalDateTime.of(2025, 5, 29, 14, 30), LocalDateTime.of(2025, 5, 29, 18, 50));

        try {
            ObjcetPlus.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(ClientPage::new);

    }
}