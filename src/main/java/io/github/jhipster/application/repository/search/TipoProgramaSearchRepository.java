package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.TipoPrograma;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TipoPrograma} entity.
 */
public interface TipoProgramaSearchRepository extends ElasticsearchRepository<TipoPrograma, Long> {
}
