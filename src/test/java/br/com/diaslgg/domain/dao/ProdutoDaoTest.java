package br.com.diaslgg.domain.dao;

import br.com.diaslgg.domain.entity.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDaoTest {

    private static IProdutoDao produtoDao;

    @BeforeAll
    static void setUp() {
        produtoDao = new ProdutoDao();
    }

    @AfterEach
    void excluirAfterTest() {
        List<Produto> produtosExcluir = produtoDao.findAll();

        if (produtosExcluir != null) {
            for (Produto produtoExcluir : produtosExcluir) {
                produtoDao.exclude(produtoExcluir);
            }
        }
    }

    @Test
    void saveTest() {
        Produto produto = new Produto("Martelo", "A1", 80.00);

        Produto produtoResponse = produtoDao.save(produto);

        assertNotNull(produtoResponse);
        assertNotNull(produtoResponse.getId());
    }

    @Test
    void findTest(){
        Produto produto = new Produto("Martelo", "A1", 80.00);

        produto = produtoDao.save(produto);

        Produto produtoToBeFound = produtoDao.find(produto.getId());

        assertNotNull(produtoToBeFound.getId());
        assertEquals(produto, produtoToBeFound);
    }

    @Test
    void findAllTest() {
        Produto produto1 = new Produto("Martelo", "A1", 80.00);
        Produto produto2 = new Produto("Prego", "A2", 0.10);

        produtoDao.save(produto1);
        produtoDao.save(produto2);

        List<Produto> produtos = produtoDao.findAll();

        assertNotNull(produtos);
        assertEquals(2, produtos.size());
    }

    @Test
    void excludeTest() {
        Produto produto = new Produto("Martelo", "A1", 80.00);

        Produto produtoSaved = produtoDao.save(produto);

        Produto produtoExcluded = produtoDao.exclude(produtoSaved);
        Produto produtoFound = produtoDao.find(produtoExcluded.getId());

        assertNull(produtoFound);
    }
}