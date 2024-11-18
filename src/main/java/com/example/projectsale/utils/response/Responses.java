package com.example.projectsale.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Responses extends Response {

    private PageableResponse meta;

    public record PageableResponse(Integer totalPage, Pageable pageable) {
    }
}
