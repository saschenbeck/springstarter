package codeup.com.blog.repos;

import codeup.com.blog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByDescription(String description);
    List<Ad> findAllByDescriptionIsLike(String term);
}
