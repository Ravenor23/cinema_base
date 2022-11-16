package com.kata.cinema.base.init;

import com.kata.cinema.base.models.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.entity.AvailableOnlineMovie;
import com.kata.cinema.base.models.entity.Collection;
import com.kata.cinema.base.models.entity.FolderMovie;
import com.kata.cinema.base.models.entity.Genre;
import com.kata.cinema.base.models.entity.Movie;
import com.kata.cinema.base.models.entity.PurchasedMovie;
import com.kata.cinema.base.models.entity.Role;
import com.kata.cinema.base.models.entity.User;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.PurchaseType;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.entity.AvailableOnlineService;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.FolderMovieService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PurchasedMovieService;
import com.kata.cinema.base.service.entity.RoleService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.service.entity.impl.CollectionServiceImp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnExpression("${app.initializer.runInitialize}")
public class DataInit {
    private final MovieService movieService;
    private final GenreService genreService;
    private final CollectionService collectionService;
    private final RoleService roleService;
    private final UserService userService;
    private final FolderMovieService folderMovieService;
    private final PurchasedMovieService purchasedMovieService;
    private final AvailableOnlineService availableOnlineService;

    private final PasswordEncoder passwordEncoder;

    public DataInit(MovieService movieService, GenreService genreService, CollectionServiceImp collectionService,
                    RoleService roleService, UserService userService, FolderMovieService folderMovieService, PurchasedMovieService purchasedMovieService, AvailableOnlineService availableOnlineService, PasswordEncoder passwordEncoder) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.collectionService = collectionService;
        this.roleService = roleService;
        this.userService = userService;
        this.folderMovieService = folderMovieService;
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
            movie.setDataRelease(LocalDate.ofEpochDay(ThreadLocalRandom.current()
                    .nextLong(LocalDate.of(1990, Month.JANUARY, 1).toEpochDay(),
                            LocalDate.now().toEpochDay())));
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
                int amount = new Random().nextInt(5,26);
                for (int j = 0; j < amount; j++) {
                    movies.add(movieList.get(new Random().nextInt(0, movieList.size())));
                }
                folderMovie.setMovies(movies);

                folderMovieService.save(folderMovie);
            }
        }
    }
}
