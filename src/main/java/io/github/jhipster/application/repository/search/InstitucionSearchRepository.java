package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Institucion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Institucion} entity.
 */
public interface InstitucionSearchRepository extends ElasticsearchRepository<Institucion, Long> {
}
