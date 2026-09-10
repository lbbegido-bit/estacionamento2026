package com.curso.estacionamento2026;

import com.curso.estacionamento2026.application.RecursoNaoEncontradoException;
import com.curso.estacionamento2026.application.TipoVeiculoService;
import com.curso.estacionamento2026.application.VagaService;
import com.curso.estacionamento2026.application.VeiculoService;
import com.curso.estacionamento2026.domain.Status;
import com.curso.estacionamento2026.domain.TipoVeiculo;
import com.curso.estacionamento2026.domain.Vaga;
import com.curso.estacionamento2026.domain.Veiculo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Estacionamento2026ApplicationTests {

    @Autowired
    private TipoVeiculoService tipoVeiculoService;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private VeiculoService veiculoService;


    @Test
    void teste01_contextoDaAplicacao() {
        assertNotNull(tipoVeiculoService);
        assertNotNull(vagaService);
        assertNotNull(veiculoService);
    }


    @Test
    void teste02_cadastrarTipoVeiculo() {
        TipoVeiculo tipo = tipoVeiculoService.cadastrar("Carro");

        assertNotNull(tipo);
        assertNotNull(tipo.getId());
        assertEquals("Carro", tipo.getNome());
    }


    @Test
    void teste03_tipoVeiculoDeveSerAtivo() {
        TipoVeiculo tipo = tipoVeiculoService.cadastrar("Moto");

        assertEquals(Status.ATIVO, tipo.getStatus());
    }


    @Test
    void teste04_buscarTipoVeiculo() {
        TipoVeiculo tipo = tipoVeiculoService.cadastrar("Caminhao");

        TipoVeiculo encontrado =
            tipoVeiculoService.buscarPorId(tipo.getId());

        assertNotNull(encontrado);
        assertEquals(tipo.getId(), encontrado.getId());
        assertEquals("Caminhao", encontrado.getNome());
    }


    @Test
    void teste05_listarTiposVeiculo() {
        tipoVeiculoService.cadastrar("SUV");

        List<TipoVeiculo> tipos =
            tipoVeiculoService.listar();

        assertNotNull(tipos);
        assertFalse(tipos.isEmpty());
    }


    @Test
    void teste06_buscarTipoVeiculoInexistente() {
        assertThrows(
            RecursoNaoEncontradoException.class,
            () -> tipoVeiculoService.buscarPorId(999999L)
        );
    }


    @Test
    void teste07_cadastrarVaga() {
        Vaga vaga =
            vagaService.cadastrar(
                new Vaga("A01", "Terreo")
            );

        assertNotNull(vaga);
        assertNotNull(vaga.getId());
        assertEquals("A01", vaga.getNumero());
        assertEquals("Terreo", vaga.getLocalizacao());
    }


    @Test
    void teste08_vagaDeveSerAtiva() {
        Vaga vaga =
            vagaService.cadastrar(
                new Vaga("A02", "Terreo")
            );

        assertEquals(Status.ATIVO, vaga.getStatus());
    }


    @Test
    void teste09_buscarVaga() {
        Vaga vaga =
            vagaService.cadastrar(
                new Vaga("B01", "Primeiro andar")
            );

        Vaga encontrada =
            vagaService.buscarPorId(vaga.getId());

        assertNotNull(encontrada);
        assertEquals(vaga.getId(), encontrada.getId());
        assertEquals("B01", encontrada.getNumero());
    }


    @Test
    void teste10_listarVagas() {
        vagaService.cadastrar(
            new Vaga("B02", "Segundo andar")
        );

        List<Vaga> vagas =
            vagaService.listar();

        assertNotNull(vagas);
        assertFalse(vagas.isEmpty());
    }


    @Test
    void teste11_buscarVagaInexistente() {
        assertThrows(
            RecursoNaoEncontradoException.class,
            () -> vagaService.buscarPorId(999999L)
        );
    }


    @Test
    void teste12_cadastrarVeiculo() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("Hatch");

        Veiculo veiculo =
            new Veiculo(
                "ABC1234",
                "Gol",
                "Preto",
                LocalDateTime.now()
            );

        Veiculo cadastrado =
            veiculoService.cadastrar(
                veiculo,
                tipo.getId()
            );

        assertNotNull(cadastrado);
        assertNotNull(cadastrado.getId());
        assertEquals("ABC1234", cadastrado.getPlaca());
        assertEquals("Gol", cadastrado.getModelo());
        assertEquals("Preto", cadastrado.getCor());
    }


    @Test
    void teste13_veiculoDeveSerAtivo() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("Sedan");

        Veiculo veiculo =
            new Veiculo(
                "DEF5678",
                "Civic",
                "Prata",
                LocalDateTime.now()
            );

        Veiculo cadastrado =
            veiculoService.cadastrar(
                veiculo,
                tipo.getId()
            );

        assertEquals(Status.ATIVO, cadastrado.getStatus());
    }


    @Test
    void teste14_veiculoDevePossuirTipo() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("SUV");

        Veiculo veiculo =
            new Veiculo(
                "GHI9012",
                "Compass",
                "Branco",
                LocalDateTime.now()
            );

        Veiculo cadastrado =
            veiculoService.cadastrar(
                veiculo,
                tipo.getId()
            );

        assertNotNull(cadastrado.getTipoVeiculo());
        assertEquals(
            tipo.getId(),
            cadastrado.getTipoVeiculo().getId()
        );
    }


    @Test
    void teste15_buscarVeiculo() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("Pickup");

        Veiculo veiculo =
            new Veiculo(
                "JKL3456",
                "Hilux",
                "Cinza",
                LocalDateTime.now()
            );

        Veiculo cadastrado =
            veiculoService.cadastrar(
                veiculo,
                tipo.getId()
            );

        Veiculo encontrado =
            veiculoService.buscarPorId(
                cadastrado.getId()
            );

        assertNotNull(encontrado);
        assertEquals(
            cadastrado.getId(),
            encontrado.getId()
        );
        assertEquals(
            "Hilux",
            encontrado.getModelo()
        );
    }


    @Test
    void teste16_listarVeiculos() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("Utilitario");

        Veiculo veiculo =
            new Veiculo(
                "MNO7890",
                "Ranger",
                "Azul",
                LocalDateTime.now()
            );

        veiculoService.cadastrar(
            veiculo,
            tipo.getId()
        );

        List<Veiculo> veiculos =
            veiculoService.listar();

        assertNotNull(veiculos);
        assertFalse(veiculos.isEmpty());
    }


    @Test
    void teste17_buscarVeiculoInexistente() {
        assertThrows(
            RecursoNaoEncontradoException.class,
            () -> veiculoService.buscarPorId(999999L)
        );
    }


    @Test
    void teste18_cadastrarVeiculoComTipoInexistente() {
        Veiculo veiculo =
            new Veiculo(
                "PQR1234",
                "Onix",
                "Vermelho",
                LocalDateTime.now()
            );

        assertThrows(
            RecursoNaoEncontradoException.class,
            () -> veiculoService.cadastrar(
                veiculo,
                999999L
            )
        );
    }


    @Test
    void teste19_dataCadastroDoVeiculo() {
        TipoVeiculo tipo =
            tipoVeiculoService.cadastrar("Eletrico");

        LocalDateTime data =
            LocalDateTime.now();

        Veiculo veiculo =
            new Veiculo(
                "STU5678",
                "BYD",
                "Azul",
                data
            );

        Veiculo cadastrado =
            veiculoService.cadastrar(
                veiculo,
                tipo.getId()
            );

        assertNotNull(cadastrado.getDataCadastro());
        assertEquals(data, cadastrado.getDataCadastro());
    }

}
