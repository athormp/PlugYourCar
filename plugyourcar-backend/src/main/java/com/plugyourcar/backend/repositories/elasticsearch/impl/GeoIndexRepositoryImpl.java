/*package com.plugyourcar.backend.repositories.elasticsearch.impl;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.plugyourcar.backend.model.elasticsearch.GeoIndex;
import com.plugyourcar.backend.repositories.elasticsearch.GeoIndexRepository;

public class GeoIndexRepositoryImpl implements GeoIndexRepository {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	public List<GeoIndex> findByLocationWithin(GeoPoint point, String distance) {

		CriteriaQuery geoLocationCriteriaQuery = new CriteriaQuery(new Criteria("location").within(point, distance + "km"));
		List<GeoIndex> geoIndexes= elasticsearchTemplate.queryForList(geoLocationCriteriaQuery, GeoIndex.class);
		return geoIndexes;
	}

	@Override
	public Class<GeoIndex> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GeoIndex> S index(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<GeoIndex> search(QueryBuilder arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GeoIndex> search(SearchQuery arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GeoIndex> search(QueryBuilder arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GeoIndex> searchSimilar(GeoIndex arg0, String[] arg1, Pageable arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<GeoIndex> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<GeoIndex> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(GeoIndex arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends GeoIndex> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<GeoIndex> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<GeoIndex> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<GeoIndex> findById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GeoIndex> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends GeoIndex> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}*/
