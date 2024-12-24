package edu.du._waxing_home.review;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // createdAt 기준 내림차순으로 정렬(최신순이 위로)
    List<Review> findAllByOrderByCreatedAtDesc();

    Optional<Review> findTopByIdLessThanOrderByIdDesc(Long id);
    Optional<Review> findTopByIdGreaterThanOrderByIdAsc(Long id);

}
