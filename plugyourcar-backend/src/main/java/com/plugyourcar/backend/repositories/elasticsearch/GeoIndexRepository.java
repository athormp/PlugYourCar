package com.plugyourcar.backend.repositories.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.plugyourcar.backend.model.elasticsearch.GeoIndex;

public interface GeoIndexRepository extends ElasticsearchRepository<GeoIndex, Integer> {

    Page<GeoIndex> findByLocationWithin(GeoPoint point, String distance, Pageable pageable);
    
}