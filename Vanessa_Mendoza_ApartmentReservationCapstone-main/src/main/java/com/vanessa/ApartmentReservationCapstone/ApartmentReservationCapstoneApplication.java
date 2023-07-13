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

	}

	private User createUser() throws Exception {
		User user = new User("SunnyDay4", "Greece88", "Margarita Dellas", "222 Putnam Ave", "555-8789", "mdella@email.com");
		return userService.createUser(user);
	}

	private Reservation createReservation(User user) throws Exception {


		Reservation reservation = new Reservation(2, 3, "August 23, 2023", "August 24, 2023", user);
		return reservationService.createReservation(reservation);
	}

}
