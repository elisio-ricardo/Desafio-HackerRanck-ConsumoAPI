package br.com.zup.importadorartigos.restResponsePage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class RestResponsePage<T> extends PageImpl<T>{

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestResponsePage(@JsonProperty("data") List<T> content,
                            @JsonProperty("page") int number,
                            @JsonProperty("per_page") int size,
                            @JsonProperty("total") Long totalElements,
                            @JsonProperty("total_pages") JsonNode pageable
                           ) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestResponsePage(List<T> content) {
        super(content);
    }

    public RestResponsePage() {
        super(new ArrayList<>());
    }
}