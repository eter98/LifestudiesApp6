package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.PasoCRM;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PasoCRM} entity.
 */
public interface PasoCRMSearchRepository extends ElasticsearchRepository<PasoCRM, Long> {
}
