package com.dittdesign.blog.models;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
	List<Article> findByTitle(String title);
	Article findById(long id);
	List<Article> findByType(String type);
	List<Article> findByType(Sort by, String string);
	List<Article> findByType(Pageable of, String string);
	List<Article> findByAuthor(String author);
	List<Article> findByAuthorAndType(String author, String type);
}
