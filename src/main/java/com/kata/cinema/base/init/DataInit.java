package com.kata.cinema.base.init;

import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.Collection;
import com.kata.cinema.base.models.entity.*;
import com.kata.cinema.base.models.entity.Profession;
import com.kata.cinema.base.models.enums.*;
import com.kata.cinema.base.service.entity.*;
import com.kata.cinema.base.service.entity.impl.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnExpression("${app.initializer.runInitialize}")
public class DataInit {
    private final MovieService movieService;
    private final MoviePersonService moviePersonService;
    private final GenreService genreService;
    private final CollectionService collectionService;
    private final RoleService roleService;
    private final UserService userService;
    private final FolderMovieService folderMovieService;
    private final PersonService personService;
    private final ProfessionService professionService;
    private final PersonMarriageService personMarriageService;
    private final PurchasedMovieService purchasedMovieService;
    private final AvailableOnlineService availableOnlineService;

    private final PasswordEncoder passwordEncoder;

    public DataInit(MovieService movieService, MoviePersonService moviePersonService, GenreService genreService, CollectionServiceImp collectionService,
                    RoleService roleService, UserService userService, FolderMovieService folderMovieService,
                    PersonService personService, ProfessionService professionService, PersonMarriageService personMarriageService, PurchasedMovieService purchasedMovieService, AvailableOnlineService availableOnlineService, PasswordEncoder passwordEncoder) {
        this.movieService = movieService;
        this.moviePersonService = moviePersonService;
        this.genreService = genreService;
        this.collectionService = collectionService;
        this.roleService = roleService;
        this.userService = userService;
        this.folderMovieService = folderMovieService;
        this.personService = personService;
        this.professionService = professionService;
        this.personMarriageService = personMarriageService;
        this.purchasedMovieService = purchasedMovieService;
        this.availableOnlineService = availableOnlineService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        createGenre();
        createMovie();
        createCollection();
        createRole();
        createUser();
        createFolderMovie();
        createPerson();
        createProfession();
        createMoviePerson();
        createPersonMarriage();
    }

    public void createGenre() {
        for (int i = 1; i <= 10; i++) {
            genreService.save(new Genre("Жанр" + i));
        }
    }

    //  for commit
    public void createMovie() {
        for (int i = 1; i <= 100; i++) {
            Movie movie = new Movie();
            movie.setName("фильм" + i);
            movie.setDataRelease(LocalDate.ofEpochDay(
                    ThreadLocalRandom.current().nextLong(
                            LocalDate.of(1990, Month.JANUARY, 1).toEpochDay(), //origin
                            LocalDate.now().toEpochDay() //bound
                    )
            ));
            movie.setDescription("описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма\n" +
                    "описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма");

            List<MPAA> mpaaList = Arrays.asList(MPAA.values());
            movie.setMpaa(mpaaList.get(new SecureRandom().nextInt(mpaaList.size())));

            List<RARS> rarsList = Arrays.asList(RARS.values());
            movie.setRars(rarsList.get(new SecureRandom().nextInt(rarsList.size())));

            List<Genre> genreList = new ArrayList<>(genreService.getAll());
            int randomSize = ThreadLocalRandom.current().nextInt(1, 4);
            Collections.shuffle(genreList);
            movie.setGenres(new HashSet<>(genreList.subList(genreList.size() - randomSize, genreList.size())));

            movieService.save(movie);

            if (i % 2 == 0) {
                AvailableOnlineMovieRequestDto availableOnlineDto = new AvailableOnlineMovieRequestDto();
                availableOnlineDto.setAvailablePlus(true);
                availableOnlineDto.setBuyPrice(i * 100);
                availableOnlineDto.setRentalPrice(i * 20);
                Movie movieForOnline = movieService.getMovieByName(movie.getName());
                availableOnlineService.save(availableOnlineDto, movieForOnline.getId());
            }
        }
    }

    public void createCollection() {
            for (int i = 1; i <= 20; i++) {
                boolean enable = !Arrays.asList(2, 6, 10, 14, 18).contains(i);
                Collection collection = new Collection("Коллекция" + i, enable);

                List<Movie> movieList = new ArrayList<>(movieService.getAll());
                int randomSize = ThreadLocalRandom.current().nextInt(5, 16);
                Collections.shuffle(movieList);
                collection.setMovies(new HashSet<>(movieList.subList(movieList.size() - randomSize, movieList.size())));

                collectionService.save(collection);
            }
    }

    public void createRole() {
        roleService.save(new Role("USER"));
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("PUBLICIST"));
    }

    public void createUser() {
        for (int i = 1; i <= 27; i++) {
            User user = new User();

            user.setEmail("email" + i + "@mail.ru");
            user.setFirstName("Имя" + i);
            user.setLastName("Фамилия" + i);
            user.setPassword(passwordEncoder.encode("password"));

            LocalDate localDate = LocalDate.ofEpochDay(
                    ThreadLocalRandom.current().nextLong(
                            LocalDate.of(1970, 1, 1).toEpochDay(),
                            LocalDate.of(2010, 12, 31).toEpochDay()
                    )
            );

            user.setBirthday(localDate);
            user.setAvatarUrl("/uploads/users/avatar/#" + i);

            Set<Role> roles = new HashSet<>(Collections.singleton(roleService.getByName("USER")));
            switch (i) {
                default -> user.setRoles(roles);
                case 26 -> {
                    roles.add(roleService.getByName("ADMIN"));
                    user.setRoles(roles);
                }
                case 27 -> {
                    roles.add(roleService.getByName("PUBLICIST"));
                    user.setRoles(roles);
                }
            }

            userService.save(user);

            for (int j = 1; j < 6; j++) {
                PurchasedMovie purchasedMovie = new PurchasedMovie();
                Random random = new Random();
                List<AvailableOnlineMovie> availableOnlineMovieList = availableOnlineService.getAll();
                int randomIndex = random.nextInt(availableOnlineMovieList.size());
                AvailableOnlineMovie movieForPurchase = availableOnlineMovieList.get(randomIndex);

                if (j % 2 == 0) {
                    purchasedMovie.setPurchase(PurchaseType.BUY);
                } else {
                    purchasedMovie.setPurchase(PurchaseType.RENT);
                }
                purchasedMovie.setEndDate(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                        .nextLong(LocalDate.of(2022, Month.DECEMBER, 1).toEpochDay(),
                                LocalDate.of(2025, 12, 1).toEpochDay())));
                purchasedMovie.setUser(userService.getUserByEmail(user.getEmail()));
                purchasedMovie.setMovie(movieForPurchase);

                purchasedMovieService.save(purchasedMovie);
            }
        }
    }

    public void createFolderMovie() {
        List<Movie> movieList = new ArrayList<>(movieService.getAll());
        for (int i = 1; i <= 27; i++) {
            for (int k = 0; k < 3; k++) {
                FolderMovie folderMovie = new FolderMovie();
                folderMovie.setUser(userService.getById((long) i));

                switch (k) {
                    case 0 -> {
                        folderMovie.setCategory(Category.WAITING_MOVIES);
                        folderMovie.setName(Category.WAITING_MOVIES.getTranslation());
                    }
                    case 1 -> {
                        folderMovie.setCategory(Category.FAVORITE_MOVIES);
                        folderMovie.setName(Category.FAVORITE_MOVIES.getTranslation());
                    }
                    case 2 -> {
                        folderMovie.setCategory(Category.VIEWED_MOVIES);
                        folderMovie.setName(Category.VIEWED_MOVIES.getTranslation());
                    }
                }

                folderMovie.setPrivacy(Privacy.PUBLIC);
                folderMovie.setDescription("описание описание описание описание описание описание описание описание ");

                Set<Movie> movies = new HashSet<>();
                int amount = new Random().nextInt(5, 26);
                for (int j = 0; j < amount; j++) {
                    movies.add(movieList.get(new Random().nextInt(0, movieList.size())));
                }
                folderMovie.setMovies(movies);

                folderMovieService.save(folderMovie);
            }
        }
    }

    public void createPerson() {
        for (int i = 1; i <= 50; i++) {
            Person person = new Person();
            person.setFirstName("FirstName " + i);
            person.setLastName("LastName " + i);
            person.setOriginalName("OriginalName " + i);
            person.setOriginalLastName("OriginalLastName " + i);
            person.setHeight(1.5 + Math.random() * 2.2);

            LocalDate localDate = LocalDate.ofEpochDay(
                    ThreadLocalRandom.current().nextLong(
                            LocalDate.of(1970, 1, 1).toEpochDay(),
                            LocalDate.of(2010, 12, 31).toEpochDay()
                    )
            );

            person.setDateBirth(localDate);
            person.setPhotoUrl("/uploads/persons/photos/#" + i);
            personService.save(person);
        }
    }

    public void createProfession() {
        ProfessionName[] professionNames = ProfessionName.values();
        for (ProfessionName professionName : professionNames) {
            Profession profession = new Profession();
            profession.setName(professionName.getTranslation());
            professionService.save(profession);
        }
    }

    public void createMoviePerson() {
        List<Profession> professions = professionService.getProfessions();
        List<Profession> nonActors = new ArrayList<>();
        Profession actor = null;
        for (Profession profession : professions) {
            if (ProfessionName.ACTOR.getTranslation().equals(profession.getName())) {
                actor = profession;
                continue;
            }
            nonActors.add(profession);
        }
        for (Movie movie : movieService.getAll()) {
            List<Person> allPersons = personService.getAll();
            Set<Person> randomPersons = new HashSet<>();
            while (randomPersons.size() < 10) {
                Person person = allPersons.get(new Random().nextInt(0, allPersons.size()));
                randomPersons.add(person);
            }

            List<Person> people = new ArrayList<>(randomPersons);
            for (int i = 0; i < people.size(); i++) {
                MoviePerson moviePerson = new MoviePerson();
                moviePerson.setId(new MoviePerson.Id(people.get(i).getId(), movie.getId()));
                moviePerson.setMovie(movie);
                moviePerson.setPerson(people.get(i));
                if (i < 3) {
                    moviePerson.setType(TypeCharacter.MAIN_CHARACTER);
                    moviePerson.setProfessions(actor);
                } else if (i < 6) {
                    moviePerson.setType(TypeCharacter.MINOR_CHARACTER);
                    moviePerson.setProfessions(actor);
                } else {
                    moviePerson.setType(TypeCharacter.NO_CHARACTER_MOVIE);
                    moviePerson.setProfessions(nonActors.get(new Random().nextInt(0, nonActors.size())));
                }
                moviePersonService.save(moviePerson);
            }
        }
    }


    public void createPersonMarriage() {
        for (int i = 1; i <= 7; i++) {

            PersonMarriage personMarriage = new PersonMarriage();
            List<Person> listPerson = new ArrayList<>(personService.getAll());
            personMarriage.setPerson(listPerson.get(new Random().nextInt(0, listPerson.size())));
            personMarriage.setHuman((listPerson.get(new Random().nextInt(0, listPerson.size()))));
            personMarriage.setMarriageStatus("marriage");
            personMarriageService.save(personMarriage);
        }
    }

}
