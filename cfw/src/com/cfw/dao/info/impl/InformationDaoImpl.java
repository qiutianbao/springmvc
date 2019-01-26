package com.cfw.dao.info.impl;

import com.cfw.dao.info.InformationDao;
import com.cfw.model.info.Information;
import core.dao.BaseDao;
import core.util.HtmlUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @
 */
@Repository
public class InformationDaoImpl extends BaseDao<Information> implements InformationDao {

	public InformationDaoImpl() {
		super(Information.class);
	}

	public void indexingInformation() {
		try {
			FullTextSession fullTextSession = Search.getFullTextSession(getSession());
			// Object information = fullTextSession.load(Information.class, new BigDecimal(99));
			// fullTextSession.index(information);
			fullTextSession.createIndexer(Information.class).threadsForSubsequentFetching(1).threadsToLoadObjects(1).startAndWait();
			fullTextSession.flushToIndexes();
			fullTextSession.getSearchFactory().optimize();
			fullTextSession.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Information> queryByInformationName(final String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		SearchFactory searchFactory = fullTextSession.getSearchFactory();
		final QueryBuilder queryBuilder = searchFactory.buildQueryBuilder().forEntity(Information.class).get();
		org.apache.lucene.search.Query luceneQuery = queryBuilder.bool().should(queryBuilder.keyword().onField("title").matching(name).createQuery())
				.should(queryBuilder.keyword().onField("author").matching(name).createQuery()).should(queryBuilder.keyword().onField("content").matching(name).createQuery()).createQuery();
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Information.class).setMaxResults(500);

		List<Information> originalInformationList = fullTextQuery.list();
		List<Information> informationList = new ArrayList<Information>();
		for (Information entity : originalInformationList) {
			Information information = new Information();
			information.setId(entity.getId());
			information.setTitle(entity.getTitle());
			information.setAuthor(entity.getAuthor());
			information.setRefreshTime(entity.getRefreshTime());
			information.setContent(entity.getContent());
			information.setContentNoHTML(HtmlUtils.htmltoText(entity.getContent()));
			informationList.add(information);
		}

		return informationList;
	}

}
