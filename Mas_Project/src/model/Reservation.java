package model;

import util.ObjcetPlus;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reservation extends ObjcetPlus {

    private Vehicle vehicle;
    private Client client;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;

    public Reservation(Vehicle vehicle, Client client, LocalDateTime reservationFrom, LocalDateTime reservationTo) {
        setVehicle(vehicle);
        setClient(client);
        setReservationFrom(reservationFrom);
        setReservationTo(reservationTo);

        if (!isTimeAvailable()) {
            removeExtent();
            throw new IllegalArgumentException("This vehicle is already reserved during the selected time period");
        }

        vehicle.addReservation(this);
        client.addReservation(this);
    }

    private boolean isTimeAvailable() {
        for (Reservation existing : vehicle.getReservations()) {
            if (datesOverlap(existing.getReservationFrom(), existing.getReservationTo(), reservationFrom, reservationTo)) {
                return false;
            }
        }
        return true;
    }

    private boolean datesOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return !(end1.isBefore(start2) || start1.isAfter(end2));
    }

    @Override
    public void removeExtent() {
        if (vehicle != null) {
            vehicle.removeReservation(this);
        }
        if (client != null) {
            client.removeReservation(this);
        }
        vehicle = null;
        client = null;
        super.removeExtent();
    }

    protected Vehicle getVehicle() {
        return vehicle;
    }

    protected Client getClient() {
        return client;
    }

    protected LocalDateTime getReservationFrom() {
        return reservationFrom;
    }

    protected LocalDateTime getReservationTo() {
        return reservationTo;
    }

    public void setVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle details cannot be null");
        }
        this.vehicle = vehicle;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client details cannot be null");
        }
        this.client = client;
    }

    public void setReservationFrom(LocalDateTime reservationFrom) {
        if (reservationFrom == null) {
            throw new IllegalArgumentException("Reservation start cannot be null");
        }
        this.reservationFrom = reservationFrom;
    }

    public void setReservationTo(LocalDateTime reservationTo) {
        if (reservationTo == null) {
            throw new IllegalArgumentException("Reservation end cannot be null");
        }
        if (reservationFrom != null && reservationTo.isBefore(reservationFrom)) {
            throw new IllegalArgumentException("Reservation end must be after start");
        }
        Duration duration = Duration.between(reservationFrom, reservationTo);
        if (duration.toMinutes() < 30) {
            throw new IllegalArgumentException("Reservation must be at least 30 minutes long");
        }

        this.reservationTo = reservationTo;
    }


    @Override
    public String toString() {
        return  vehicle.getBrand() + " " +
                 vehicle.getModel() + " " +
                  vehicle.getYearOfProduction() + " " +
                "From: " + reservationFrom.getYear() + " " + reservationFrom.getMonth() +  " " +
        reservationFrom.getHour() + ':' + reservationFrom.getMinute() +
                "To: " + reservationTo.getYear() + " " + reservationTo.getMonth() + " " +
                reservationTo.getHour() + ':' + reservationTo.getMinute() ;
    }

}
