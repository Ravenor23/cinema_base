package com.kata.cinema.base.models.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies") // таблица отвечающая за фильмы/сериалы
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name") // наименование
    private String name;

    @Column(name = "countries") // страны производства
    private String countries;

    @Column(name = "data_release") // дата выхода
    private String dataRelease;

    @Column(name = "rars")  // возрастная классификация информационной продукции в России
    private Integer rars;

    @Column(name = "mpaa")  // система рейтингов Американской киноассоциации

    private Double mpaa;

    @Column(name = "time") // продолжительность фильма
    private String time;

    @Column(name = "description") // описание фильма
    private String description;

    @Column(name = "type") // тип сериал/фильм, принимает следующее значение MOVIES, SERIALS
    private String type;

    @Column(name = "origin_name")  // оригинальное наименование фильма
    private String originName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection_movie",
            joinColumns = @JoinColumn(name = "movie_id"))
    private List<Collection> collections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"))
    private List<Genre> movieId;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "folder_movies_to_movie",
//            joinColumns = @JoinColumn(name = "movies_id"))
//    private List<FoldersMovie> foldersMovies;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "movie_person",
//            joinColumns = @JoinColumn(name = "movie_id"))
//    private List<Person> persons;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "awards_ceremony_result",
//            joinColumns = @JoinColumn(name = "movie_id"))
//    private List<AwardsCeremonyResult> awardsCeremonyResults;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<Content> contents;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<Score> scores;

    public Movie(long id, String name, String countries, String dataRelease, int rars, double mpaa, String time,
                 String description, String type, String originName, List<Collection> collections, List<Genre> movieId,
                 // List<FoldersMovie> foldersMovies, List<Person> persons, List<AwardsCeremonyResult> awardsCeremonyResults,
                 List<Content> contents, List<Score> scores) {
        this.id = id;
        this.name = name;
        this.countries = countries;
        this.dataRelease = dataRelease;
        this.rars = rars;
        this.mpaa = mpaa;
        this.time = time;
        this.description = description;
        this.type = type;
        this.originName = originName;
        this.collections = collections;
        this.movieId = movieId;
//        this.foldersMovies = foldersMovies;
//        this.persons = persons;
//        this.awardsCeremonyResults = awardsCeremonyResults;
        this.contents = contents;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getDataRelease() {
        return dataRelease;
    }

    public void setDataRelease(String dataRelease) {
        this.dataRelease = dataRelease;
    }

    public int getRars() {
        return rars;
    }

    public void setRars(int rars) {
        this.rars = rars;
    }

    public double getMpaa() {
        return mpaa;
    }

    public void setMpaa(double mpaa) {
        this.mpaa = mpaa;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<Genre> getMovieId() {
        return movieId;
    }

    public void setMovieId(List<Genre> movieId) {
        this.movieId = movieId;
    }

//    public List<FoldersMovie> getFoldersMovies() {
//        return foldersMovies;
//    }
//
//    public void setFoldersMovies(List<FoldersMovie> foldersMovies) {
//        this.foldersMovies = foldersMovies;
//    }
//
//    public List<Person> getPersons() {
//        return persons;
//    }
//
//    public void setPersons(List<Person> persons) {
//        this.persons = persons;
//    }
//
//    public List<AwardsCeremonyResult> getAwardsCeremonyResults() {
//        return awardsCeremonyResults;
//    }
//
//    public void setAwardsCeremonyResults(List<AwardsCeremonyResult> awardsCeremonyResults) {
//        this.awardsCeremonyResults = awardsCeremonyResults;
//   }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Movie() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && rars == movie.rars && Double.compare(movie.mpaa, mpaa) == 0
                && Objects.equals(name, movie.name) && Objects.equals(countries, movie.countries)
                && Objects.equals(dataRelease, movie.dataRelease) && Objects.equals(time, movie.time)
                && Objects.equals(description, movie.description) && Objects.equals(type, movie.type)
                && Objects.equals(originName, movie.originName) && Objects.equals(collections, movie.collections)
                && Objects.equals(movieId, movie.movieId)
                // && Objects.equals(foldersMovies, movie.foldersMovies) && Objects.equals(persons, movie.persons) && Objects.equals(awardsCeremonyResults, movie.awardsCeremonyResults)
                && Objects.equals(contents, movie.contents) && Objects.equals(scores, movie.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries, dataRelease, rars, mpaa, time, description, type, originName, collections, movieId,
                // foldersMovies, persons, awardsCeremonyResults,
                contents, scores);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries='" + countries + '\'' +
                ", dataRelease='" + dataRelease + '\'' +
                ", rars=" + rars +
                ", mpaa=" + mpaa +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", originName='" + originName + '\'' +
                ", collections=" + collections +
                ", movieId=" + movieId +
//                ", foldersMovies=" + foldersMovies +
//                ", persons=" + persons +
//                ", awardsCeremonyResults=" + awardsCeremonyResults +
                ", contents=" + contents +
                ", scores=" + scores +
                '}';
    }
}

