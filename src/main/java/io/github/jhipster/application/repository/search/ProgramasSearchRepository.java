package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Programas;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Programas} entity.
 */
public interface ProgramasSearchRepository extends ElasticsearchRepository<Programas, Long> {
}
