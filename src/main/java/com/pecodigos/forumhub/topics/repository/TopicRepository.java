package com.pecodigos.forumhub.topics.repository;

import com.pecodigos.forumhub.topics.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t from Topic t ORDER BY t.createdAt ASC")
    Page<Topic> findTenOldestTopics(Pageable pageable);

    boolean existsByTitle(String title);
    boolean existsByContent(String content);
}
