package gui.help;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ReservationDialog extends JDialog {
    private final JSpinner fromSpinner;
    private final JSpinner toSpinner;
    private boolean confirmed = false;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public ReservationDialog(JFrame parent) {
        super(parent, "Reservation", true);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Date From:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        fromSpinner = new JSpinner(new SpinnerDateModel());
        fromSpinner.setEditor(new JSpinner.DateEditor(fromSpinner, "yyyy-MM-dd HH:mm"));
        formPanel.add(fromSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Date To:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        toSpinner = new JSpinner(new SpinnerDateModel());
        toSpinner.setEditor(new JSpinner.DateEditor(toSpinner, "yyyy-MM-dd HH:mm"));
        formPanel.add(toSpinner, gbc);

        add(formPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        confirmButton.addActionListener(e -> {
            Date from = (Date) fromSpinner.getValue();
            Date to = (Date) toSpinner.getValue();

            fromDate = LocalDateTime.ofInstant(from.toInstant(), ZoneId.systemDefault());
            toDate = LocalDateTime.ofInstant(to.toInstant(), ZoneId.systemDefault());

            confirmed = true;
            dispose();
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }
}
