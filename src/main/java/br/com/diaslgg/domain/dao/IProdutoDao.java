package br.com.diaslgg.domain.dao;

import br.com.diaslgg.domain.entity.Produto;

import java.util.List;

public interface IProdutoDao {

    Produto save(Produto produto);

    Produto find(Long id);

    List<Produto> findAll();

    Produto exclude(Produto produto);
}
