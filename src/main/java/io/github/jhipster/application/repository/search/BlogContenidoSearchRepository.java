package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.BlogContenido;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link BlogContenido} entity.
 */
public interface BlogContenidoSearchRepository extends ElasticsearchRepository<BlogContenido, Long> {
}
