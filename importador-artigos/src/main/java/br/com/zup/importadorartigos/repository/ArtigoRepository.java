package br.com.zup.importadorartigos.repository;


import br.com.zup.importadorartigos.model.Artigo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
    Page<Artigo> findAll(Pageable pageable);
}
