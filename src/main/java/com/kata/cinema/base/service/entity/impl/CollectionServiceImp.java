package src.src.main.java.com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entity.Collection;
import src.src.main.java.com.kata.cinema.base.repositories.CollectionRepository;
import org.springframework.stereotype.Service;
import src.src.main.java.com.kata.cinema.base.service.entity.impl.CollectionService;


@Service
public class CollectionServiceImp implements CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionServiceImp(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }
}
