package io.github.jhipster.application.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link BlogContenidoSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class BlogContenidoSearchRepositoryMockConfiguration {

    @MockBean
    private BlogContenidoSearchRepository mockBlogContenidoSearchRepository;

}
