package com.erivelton.campeonato.infraestrutura.configuracao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ErroDTO {

    private Integer status;

    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private String title;

    private List<DetalhesErroDTO> details = new ArrayList<>();

    public ErroDTO(Integer status, LocalDateTime timestamp, String title) {
        this.status = status;
        this.timestamp = timestamp;
        this.title = title;
    }

    public ErroDTO(Integer status, LocalDateTime timestamp, String title, List<DetalhesErroDTO> details) {
        this.status = status;
        this.timestamp = timestamp;
        this.title = title;
        this.details.addAll(details);
    }
}
