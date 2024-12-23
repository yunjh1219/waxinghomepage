package edu.du._waxing_home.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    // createdAt 기준 내림차순으로 정렬(최신순이 위로)
    List<News> findAllByOrderByCreatedAtDesc();

    Optional<News> findTopByIdLessThanOrderByIdDesc(Long id);
    Optional<News> findTopByIdGreaterThanOrderByIdAsc(Long id);
}
