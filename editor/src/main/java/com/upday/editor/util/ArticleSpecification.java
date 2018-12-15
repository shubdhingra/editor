package com.upday.editor.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.upday.editor.dao.entity.ArticleEntity;

/**
 * Specification
 * 
 * @author Shubham Dhingra
 *
 */
public class ArticleSpecification implements Specification<ArticleEntity> {

	private ArticleEntity articleFilter;

	public ArticleSpecification(final ArticleEntity article) {
		super();
		this.articleFilter = article;
	}

	/**
	 * logic implemented for filtering/creating the specification for the DB 
	 */
	@Override
	public Predicate toPredicate(final Root<ArticleEntity> root, final CriteriaQuery<?> query,
			final CriteriaBuilder cb) {

		Predicate p = cb.disjunction();
		List<Predicate> filters = new ArrayList<>();
		if (articleFilter.getAuthor() != null) {
			filters.add(cb.equal(root.get("author"), articleFilter.getAuthor()));
		}

		if (articleFilter.getKeywords() != null) {
			filters.add(cb.equal(root.get("keywords"), articleFilter.getKeywords()));
		}

		Predicate[] f = new Predicate[filters.size()];
		p.getExpressions().add(cb.and(filters.toArray(f)));
		return p;
	}

}
