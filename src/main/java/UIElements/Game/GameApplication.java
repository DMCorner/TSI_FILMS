package UIElements.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin

public class GameApplication {

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	public GameApplication(ActorRepository ar, CategoryRepository cr, FilmRepository fr){
		actorRepository = ar;
		categoryRepository = cr;
		filmRepository = fr;
	}

		public static void main(String[] args) {
			SpringApplication.run(GameApplication.class, args);
		}

		// -----ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS----------------
		// -----ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS-ACTORS----------------

		@GetMapping("/allactors")
		public @ResponseBody Iterable<Actor> getAllActors() {
		return actorRepository.findAll();
		}

		@GetMapping("/allactors/{id}")
		public Optional<Actor> getActorFromID(@PathVariable Integer id) {
		return actorRepository.findById(id);
		}
		//set a put mapping in allactors then dependent on ID
		@PutMapping("/allactors/{id}")
		// make a public object that deals with the actor list
		public Optional<Actor> replaceActor(@RequestBody Actor newActor, @PathVariable Integer id) {

			return Optional.of(actorRepository.findById(id)
					.map(Actor -> {
						Actor.setFirstname(newActor.getFirstname());
						Actor.setLastname(newActor.getLastname());
						return actorRepository.save(Actor);
					})
					.orElseGet(() -> {
						newActor.setActor_ID(id);
						return actorRepository.save(newActor);
					}));
		}
		@DeleteMapping("/allactors/{id}")
		public void  deleteActor(@PathVariable Integer id) {
			actorRepository.deleteById(id);
		}

		//----------CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-------------
		//----------CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-CATEGORIES-------------

		@GetMapping("/allcategories")
		public @ResponseBody Iterable<Category> getAllCategories() {
			return categoryRepository.findAll();
		}

		@GetMapping("/allcategories/{id}")
		public Optional<Category> getCategoryFromID(@PathVariable Integer id) {
			return categoryRepository.findById(id);
		}
		//set a put mapping in allactors then dependent on ID
		@PutMapping("/allcategories/{id}")
		// make a public object that deals with the actor list
		public Optional<Category> replaceCategory(@RequestBody Category newCategory, @PathVariable Integer id) {

			return Optional.of(categoryRepository.findById(id)
					.map(Category -> {
						Category.setCategory_name(newCategory.getCategory_name());
						return categoryRepository.save(Category);
					})
					.orElseGet(() -> {
						newCategory.setCategory_ID(id);
						return categoryRepository.save(newCategory);
					}));
		}
		@DeleteMapping("/allcategories/{id}")
		public void  deleteCategories(@PathVariable Integer id) {
			categoryRepository.deleteById(id);
		}

		// -------------FILMS-FILMS-FILMS-FILMS-FILMS-FILMS-FILMS-FILMS-FILMS-FILMS-FILMS--------------------

		@GetMapping("/allfilms")
		public @ResponseBody Iterable<Film> getAllFilms() {
			return filmRepository.findAll();
		}

		@GetMapping("/allfilms/{id}")
		public Optional<Film> getFilmFromID(@PathVariable Integer id) {
			return filmRepository.findById(id);
		}
		//set a put mapping in allactors then dependent on ID
		@PutMapping("/allfilms/{id}")
		// make a public object that deals with the actor list
		public Optional<Film> replaceActor(@RequestBody Film newFilm, @PathVariable Integer id) {

			return Optional.of(filmRepository.findById(id)
					.map(Film -> {
						Film.setTitle(newFilm.getTitle());
						Film.setDescription(newFilm.getDescription());
						return filmRepository.save(Film);
					})
					.orElseGet(() -> {
						newFilm.setFilm_ID(id);
						return filmRepository.save(newFilm);
					}));
		}
		@DeleteMapping("/allfilms/{id}")
		public void  deleteFilm(@PathVariable Integer id) {
		filmRepository.deleteById(id);
		}


//
//		@GetMapping("/allactors/{id}")
//		Actor newActor(@RequestBody Actor newActor) {
//		return actorRepository.save(newActor);
//		}


//		public @ResponseBody Iterable<Actor> getOneActor() {
//
//		return actorRepository.findById(id);



	}
