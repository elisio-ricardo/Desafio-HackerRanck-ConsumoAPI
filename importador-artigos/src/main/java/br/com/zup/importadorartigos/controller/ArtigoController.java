package br.com.zup.importadorartigos.controller;


import br.com.zup.importadorartigos.dto.ArtigoMapper;
import br.com.zup.importadorartigos.model.Artigo;
import br.com.zup.importadorartigos.restResponsePage.RestResponsePage;
import br.com.zup.importadorartigos.service.ArtigoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/artigos/importador")
public class ArtigoController {

    public final ArtigoService artigoService;


    public ArtigoController(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    @PostMapping("{autor}")
    public ResponseEntity<Page<Artigo>> postArtigo(@PathVariable String autor,
                                                   @PageableDefault Pageable pageable) {
        String url = "https://jsonmock.hackerrank.com/api/articles?author=" + autor;

        RestTemplate restTemplate = new RestTemplate();


        ParameterizedTypeReference<RestResponsePage<Artigo>> responseType =
                new ParameterizedTypeReference<RestResponsePage<Artigo>>() {
                };

        ResponseEntity<RestResponsePage<Artigo>> result = restTemplate.exchange(url, HttpMethod.GET,
                null, responseType);

        List<Artigo> artigoResult = result.getBody().getContent();

        for (Artigo artigo : artigoResult) {
            Artigo artigoPersistido = new Artigo();
            if (artigo.getTitle() != null) {
                artigoPersistido.setTitle(artigo.getTitle());
                artigoPersistido.setStory_title(artigo.getStory_title());
            }
            else if (artigo.getTitle() == null && artigo.getStory_title() != null) {
                artigoPersistido.setTitle(artigo.getStory_title());
                artigoPersistido.setStory_title(artigo.getStory_title());
            }
            else if (artigo.getTitle() == null && artigo.getStory_title() == null) {
                continue;
            }
            artigoService.save(artigoPersistido);
        }
        Page<Artigo> artigos = artigoService.listarTodosArtigos(pageable).map(ArtigoMapper::fromEntity);

        return ResponseEntity.ok(artigos);
    }

}

