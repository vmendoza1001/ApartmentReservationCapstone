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

//		testGetUser();
	}

	private User createUser() throws Exception {
		User user = new User("ChickenDinner5", "Feed43$", "Frank DelValle", "378 Disney Ave", "555-7858", "hiya@email.com");
		return userService.createUser(user);
	}

	private Reservation createReservation(User user) throws Exception {


		Reservation reservation = new Reservation("R5440", 8, "September 23, 2023", "September 24, 2023", user);
		return reservationService.createReservation(reservation);
	}

}
