package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.PerfilUsuario;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PerfilUsuario} entity.
 */
public interface PerfilUsuarioSearchRepository extends ElasticsearchRepository<PerfilUsuario, Long> {
}
