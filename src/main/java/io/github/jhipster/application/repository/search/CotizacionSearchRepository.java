package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Cotizacion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Cotizacion} entity.
 */
public interface CotizacionSearchRepository extends ElasticsearchRepository<Cotizacion, Long> {
}
