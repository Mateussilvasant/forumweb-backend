package br.com.mateussilvasant.forumweb.api.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EPontos {
    COMENTARIO(3), TOPICO(10);

    private int valor;
}