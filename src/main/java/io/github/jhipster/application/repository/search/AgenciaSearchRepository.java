package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Agencia;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Agencia} entity.
 */
public interface AgenciaSearchRepository extends ElasticsearchRepository<Agencia, Long> {
}
