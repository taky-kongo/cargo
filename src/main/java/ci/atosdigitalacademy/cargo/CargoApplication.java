package ci.atosdigitalacademy.cargo;

import ci.atosdigitalacademy.cargo.services.RoleService;
import ci.atosdigitalacademy.cargo.services.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class CargoApplication implements CommandLineRunner {

	private final RoleService roleService;

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
	}
}
