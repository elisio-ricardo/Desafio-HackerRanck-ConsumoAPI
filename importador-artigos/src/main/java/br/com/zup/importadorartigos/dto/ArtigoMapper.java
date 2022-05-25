package br.com.zup.importadorartigos.dto;

import br.com.zup.importadorartigos.model.Artigo;

import java.util.stream.Collectors;

public class ArtigoMapper {


    public static Artigo fromEntity(Artigo artigo) {
        return new Artigo(artigo.getId(), artigo.getTitle(), artigo.getStory_title());
    }

}
