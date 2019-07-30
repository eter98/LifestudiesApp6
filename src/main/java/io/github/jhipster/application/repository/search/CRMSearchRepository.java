package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.CRM;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CRM} entity.
 */
public interface CRMSearchRepository extends ElasticsearchRepository<CRM, Long> {
}
