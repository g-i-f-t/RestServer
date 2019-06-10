package kr.ac.jejunu.giftrestserver.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private Integer code;
    private String messages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
