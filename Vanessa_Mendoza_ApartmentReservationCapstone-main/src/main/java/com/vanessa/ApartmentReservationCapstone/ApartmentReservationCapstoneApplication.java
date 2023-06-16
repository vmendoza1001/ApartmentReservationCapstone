package com.vanessa.ApartmentReservationCapstone;

import com.vanessa.ApartmentReservationCapstone.model.Reservation;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.service.ReservationService;
import com.vanessa.ApartmentReservationCapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication(scanBasePackages = "com.vanessa")
@ComponentScan(basePackages = "com.vanessa")
public class ApartmentReservationCapstoneApplication implements CommandLineRunner {

	private final UserService userService;
	private final ReservationService reservationService;

	@Autowired
	public ApartmentReservationCapstoneApplication(UserService userService, ReservationService reservationService) {
		this.userService = userService;
		this.reservationService = reservationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApartmentReservationCapstoneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User newUser = createUser();
		System.out.println("Created User: " + newUser);

		Reservation newReservation = createReservation(newUser);
		System.out.println("Created Reservation: " + newReservation);

		testGetUser();
	}

	private User createUser() throws Exception {
		User user = new User("MLars11", "WeistFlower$", "Grace", "Pate", "33 Market Street", "555-8668", "vtpo01@email.com");
		return userService.createUser(user);
	}

	private Reservation createReservation(User user) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = dateFormat.parse("1/28/2023");
		Date endDate = dateFormat.parse("1/29/2023");

		Reservation reservation = new Reservation("R5440", 3, startDate, endDate, user);
		return reservationService.createReservation(reservation);
	}

	private void testGetUser() {
		User user = userService.getUser(4);

		if (user != null) {
			System.out.println("User Details: " + user.getId() + " | " + user.getFirstName() +
					" | " + user.getLastName() + " | " + user.getEmailAddress());


		} else {
			System.out.println("User not found!");
		}
	}
}
