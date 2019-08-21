package mikolo.microserviceUser.resource;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import mikolo.microserviceUser.model.User;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/userclient")
public class UserResource {

	private RestTemplate restTemplate;

	@GetMapping(value = "/id={id}")
	private User getUserById(@PathVariable("id") long id) {
		return restTemplate.exchange("http://localhost:8300/service/users/id=" + id,
				HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
				}).getBody();
	}

	@GetMapping(value = "/email={email}")
	private User getUserByEmail(@PathVariable("email") String email) {
		ResponseEntity<User> userEntity = restTemplate.exchange("http://localhost:8300/service/users/email=" + email,
				HttpMethod.GET, null, new ParameterizedTypeReference<User>() {
				});
		return userEntity.getBody();
	}

	@GetMapping(value = "/all")
	private List<User> getAll() {
		ResponseEntity<List<User>> userEntity = restTemplate.exchange("http://localhost:8300/service/users/all",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
				});
		return userEntity.getBody();
	}

	@PostMapping("/adduser")
	private User addUser(@RequestBody final User user) {
		restTemplate.postForObject("http://localhost:8300/service/users/adduser", user, String.class);
		return getUserByEmail(user.getEmail());
	}

}
