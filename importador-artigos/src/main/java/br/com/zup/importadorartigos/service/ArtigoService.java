package br.com.zup.importadorartigos.service;


import br.com.zup.importadorartigos.dto.ArtigoMapper;
import br.com.zup.importadorartigos.model.Artigo;
import br.com.zup.importadorartigos.repository.ArtigoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArtigoService {

    private final ArtigoRepository artigoRepository;

    public ArtigoService(ArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public void save(Artigo artigoPersistido) {
        artigoRepository.save(artigoPersistido);
    }

    public Page<Artigo> listarTodosArtigos(Pageable pageable) {
        return artigoRepository.findAll(pageable);
    }
}
