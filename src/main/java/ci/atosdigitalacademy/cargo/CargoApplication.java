package ci.atosdigitalacademy.cargo;

import ci.atosdigitalacademy.cargo.services.*;
import ci.atosdigitalacademy.cargo.services.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class CargoApplication implements CommandLineRunner {

	private final RoleService roleService;
	private final UserService userService;
	private final ClientService clientService;
	private final CompanyService companyService;
	private final VoyageService voyageService;
	private final SeatService seatService;

	public static void main(String[] args) {
		SpringApplication.run(CargoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<RoleDTO> roles = new ArrayList<>();

		RoleDTO roleAdmin = new RoleDTO();
		roleAdmin.setRole("ADMIN");
		roles.add(roleAdmin);

		RoleDTO roleUser = new RoleDTO();
		roleUser.setRole("USER");
		roles.add(roleUser);

		RoleDTO roleCompany = new RoleDTO();
		roleCompany.setRole("COMPANY");
		roles.add(roleCompany);

		roleService.initRoles(roles);

		// Ajout des Users
		UserDTO user1 = new UserDTO();
		user1.setUsername("user1");
		user1.setPassword("password1");

		user1 = userService.saveUser(user1);

		UserDTO user2 = new UserDTO();
		user2.setUsername("user2");
		user2.setPassword("password2");

		user2 = userService.saveUser(user2);

		UserDTO user3 = new UserDTO();
		user3.setUsername("user3");
		user3.setPassword("password3");

		user3 = userService.saveUser(user3);

		// Ajout des clients
		ClientDTO clientDTO1 = new ClientDTO();
		clientDTO1.setName("user1");
		clientDTO1.setPhoneNumber("0234028990");
		clientDTO1.setEmail("user1@gmail.com");
		clientDTO1.setDateCreation(LocalDate.now());
		clientDTO1.setUser(user1);

		clientDTO1 = clientService.save(clientDTO1);

		ClientDTO clientDTO2 = new ClientDTO();
		clientDTO2.setName("user2");
		clientDTO2.setPhoneNumber("0734028990");
		clientDTO2.setEmail("user2@gmail.com");
		clientDTO2.setDateCreation(LocalDate.now());
		clientDTO2.setUser(user2);

		clientDTO2 = clientService.saveClient(clientDTO2);

		ClientDTO clientDTO3 = new ClientDTO();
		clientDTO3.setName("user3");
		clientDTO3.setPhoneNumber("0834028990");
		clientDTO3.setEmail("user3@gmail.com");
		clientDTO3.setDateCreation(LocalDate.now());
		clientDTO3.setUser(user3);

		clientDTO3 = clientService.saveClient(clientDTO3);

		// Ajout des users pour les compagnies
		UserDTO userVoyage1 = new UserDTO();
		userVoyage1.setUsername("userVoyage1");
		userVoyage1.setPassword("passwordVoyage1");

		userVoyage1 = userService.saveUser(userVoyage1);

		UserDTO userVoyage2 = new UserDTO();
		userVoyage2.setUsername("userVoyage2");
		userVoyage2.setPassword("passwordVoyage2");

		userVoyage2 = userService.saveUser(userVoyage2);

		UserDTO userVoyage3 = new UserDTO();
		userVoyage3.setUsername("userVoyage3");
		userVoyage3.setPassword("passwordVoyage3");

		userVoyage3 = userService.saveUser(userVoyage3);

		CompanyDTO company1 = new CompanyDTO();
		company1.setName("company1");
		company1.setPhoneNumber("0234828990");
		company1.setEmail("company1@gmail.com");
		company1.setDateCreation(LocalDate.now());
		company1.setUser(userVoyage1);

		company1 = companyService.saveCompany(company1);

		CompanyDTO company2 = new CompanyDTO();
		company2.setName("company2");
		company2.setPhoneNumber("0734828690");
		company2.setEmail("company2@gmail.com");
		company2.setDateCreation(LocalDate.now());
		company2.setUser(userVoyage2);

		company2 = companyService.saveCompany(company2);

		CompanyDTO company3 = new CompanyDTO();
		company3.setName("company3");
		company3.setPhoneNumber("0834828997");
		company3.setEmail("company3@gmail.com");
		company3.setDateCreation(LocalDate.now());
		company3.setUser(userVoyage3);

		company3 = companyService.saveCompany(company3);

		// Ajout des voyages
		VoyageDTO voyage1 = new VoyageDTO();
		voyage1.setStart("Abidjan");
		voyage1.setDestination("Yamoussoukro");
		voyage1.setAmount(5500D);
		voyage1.setCompany(company1);
		voyage1.setDateVoyage(LocalDate.parse("2024-10-18"));
		voyage1.setTime("4");

		voyage1 = voyageService.saveVoyage(voyage1);

		VoyageDTO voyage2 = new VoyageDTO();
		voyage2.setStart("Abidjan");
		voyage2.setDestination("Yamoussoukro");
		voyage2.setAmount(5500D);
		voyage2.setCompany(company1);
		voyage2.setDateVoyage(LocalDate.parse("2024-10-19"));
		voyage2.setTime("3.5");

		voyage2 = voyageService.saveVoyage(voyage2);

		VoyageDTO voyage3 = new VoyageDTO();
		voyage3.setStart("Abidjan");
		voyage3.setDestination("Bouaké");
		voyage3.setCompany(company1);
		voyage3.setAmount(6500D);
		voyage3.setDateVoyage(LocalDate.parse("2024-10-17"));
		voyage3.setTime("6");

		voyage3 = voyageService.saveVoyage(voyage3);

		VoyageDTO voyage4 = new VoyageDTO();
		voyage4.setStart("Abidjan");
		voyage4.setDestination("Yamoussoukro");
		voyage4.setAmount(4500D);
		voyage4.setCompany(company2);
		voyage4.setDateVoyage(LocalDate.parse("2024-10-18"));
		voyage4.setTime("4.5");

		voyage4 = voyageService.saveVoyage(voyage4);

		VoyageDTO voyage5 = new VoyageDTO();
		voyage5.setStart("Yamoussoukro");
		voyage5.setDestination("Abidjan");
		voyage5.setAmount(5500D);
		voyage5.setCompany(company2);
		voyage5.setDateVoyage(LocalDate.parse("2024-10-19"));
		voyage5.setTime("4");

		voyage5 = voyageService.saveVoyage(voyage5);

		VoyageDTO voyage6 = new VoyageDTO();
		voyage6.setStart("Bouaké");
		voyage6.setDestination("Yamoussoukro");
		voyage6.setAmount(2500D);
		voyage6.setCompany(company2);
		voyage6.setDateVoyage(LocalDate.parse("2024-10-17"));
		voyage6.setTime("2");

		voyage6 = voyageService.saveVoyage(voyage6);

		VoyageDTO voyage7 = new VoyageDTO();
		voyage7.setStart("Abidjan");
		voyage7.setDestination("Gagnoa");
		voyage7.setAmount(5500D);
		voyage7.setCompany(company3);
		voyage7.setDateVoyage(LocalDate.parse("2024-10-18"));
		voyage7.setTime("5");

		voyage7 = voyageService.saveVoyage(voyage7);

		VoyageDTO voyage8 = new VoyageDTO();
		voyage8.setStart("Abidjan");
		voyage8.setDestination("Yamoussoukro");
		voyage8.setAmount(4500D);
		voyage8.setCompany(company3);
		voyage8.setDateVoyage(LocalDate.parse("2024-10-19"));
		voyage8.setTime("5");

		voyage8 = voyageService.saveVoyage(voyage8);

		VoyageDTO voyage9 = new VoyageDTO();
		voyage9.setStart("Abidjan");
		voyage9.setDestination("Abengourou");
		voyage9.setAmount(2500D);
		voyage9.setCompany(company3);
		voyage9.setDateVoyage(LocalDate.parse("2024-10-17"));
		voyage9.setTime("1.5");

		voyage9 = voyageService.saveVoyage(voyage9);

		SeatDTO seat1 = new SeatDTO();
		seat1.setSeatNumber(1);
		seat1.setClasse("Standard");
		seat1.setStatus("Actif");
		seat1.setVoyage(voyage1);

		seat1 = seatService.saveSeat(seat1);

		SeatDTO seat2 = new SeatDTO();
		seat2.setSeatNumber(2);
		seat2.setClasse("Standard");
		seat2.setStatus("Actif");
		seat2.setVoyage(voyage1);

		seat2 = seatService.saveSeat(seat2);

		SeatDTO seat3 = new SeatDTO();
		seat3.setSeatNumber(3);
		seat3.setClasse("Class");
		seat3.setStatus("Actif");
		seat3.setVoyage(voyage1);

		seat3 = seatService.saveSeat(seat3);

		SeatDTO seat4 = new SeatDTO();
		seat4.setSeatNumber(1);
		seat4.setClasse("Class");
		seat4.setStatus("Actif");
		seat4.setVoyage(voyage2);

		seat4 = seatService.saveSeat(seat4);

		SeatDTO seat5 = new SeatDTO();
		seat5.setSeatNumber(2);
		seat5.setClasse("Medium");
		seat5.setStatus("Actif");
		seat5.setVoyage(voyage2);

		seat5 = seatService.saveSeat(seat5);

		SeatDTO seat6 = new SeatDTO();
		seat6.setSeatNumber(3);
		seat6.setClasse("Class");
		seat6.setStatus("Actif");
		seat6.setVoyage(voyage2);

		seat6 = seatService.saveSeat(seat6);

		SeatDTO seat7 = new SeatDTO();
		seat7.setSeatNumber(1);
		seat7.setClasse("Standard");
		seat7.setStatus("Actif");
		seat7.setVoyage(voyage3);

		seat7 = seatService.saveSeat(seat7);

		SeatDTO seat8 = new SeatDTO();
		seat8.setSeatNumber(2);
		seat8.setClasse("Standard");
		seat8.setStatus("Actif");
		seat8.setVoyage(voyage3);

		seat8 = seatService.saveSeat(seat8);

		SeatDTO seat9 = new SeatDTO();
		seat9.setSeatNumber(3);
		seat9.setClasse("Class");
		seat9.setStatus("Actif");
		seat9.setVoyage(voyage3);

		seat9 = seatService.saveSeat(seat9);
	}
}
