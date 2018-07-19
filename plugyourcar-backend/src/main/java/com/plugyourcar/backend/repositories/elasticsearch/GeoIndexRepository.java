package com.plugyourcar.backend.repositories.elasticsearch;

import com.plugyourcar.backend.model.elasticsearch.GeoIndex;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GeoIndexRepository extends ElasticsearchRepository<GeoIndex, String> {

    Page<GeoIndex> findByLocationNear(GeoPoint point, String distance);
    
}