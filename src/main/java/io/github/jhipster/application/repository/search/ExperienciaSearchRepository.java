package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Experiencia;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Experiencia} entity.
 */
public interface ExperienciaSearchRepository extends ElasticsearchRepository<Experiencia, Long> {
}
