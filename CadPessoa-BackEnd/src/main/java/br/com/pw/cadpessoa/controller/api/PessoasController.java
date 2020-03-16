package br.com.pw.cadpessoa.controller.api;

import br.com.pw.cadpessoa.controller.PessoasValidation;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pw.cadpessoa.entity.Pessoa;
import java.util.Date;
import br.com.pw.cadpessoa.repository.Pessoas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/pessoas")
@Api(tags = "Pessoas", description = "API de Cadastro de Pessoas")
public class PessoasController {

    @Autowired
    private Pessoas pessoas;

    @ApiOperation(value = "Adicionar Pessoa")
    @PostMapping("/v1")
    public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa) {
        pessoa.setDataCadastro(new Date(System.currentTimeMillis()));
        
        ResponseEntity validacao = PessoaValidacao(pessoa);
        if(validacao != null){
            return validacao;
        }
        
        return ResponseEntity.ok(pessoas.save(pessoa));
    }

    @ApiOperation(value = "Lista as Pessoas")
    @GetMapping
    public List<Pessoa> listar() {
        return pessoas.findAll();
    }
    

    @ApiOperation(value = "Busca uma Pessoa pelo CPF")
    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> buscar(@PathVariable String cpf) {
        Pessoa pessoa = pessoas.getOne(cpf);

        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pessoa);
    }

    @ApiOperation(value = "Atualiza os dados de uma Pessoa pelo CPF")
    @PutMapping("/v1/{cpf}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable String cpf,
            @Valid @RequestBody Pessoa pessoa) {
        Pessoa existente = pessoas.getOne(cpf);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        //Validacao regras negocio
         ResponseEntity validacao = PessoaValidacao(pessoa);
        if(validacao != null){
            return validacao;
        }
        
        existente.setDataAtualizacao(new Date(System.currentTimeMillis()));

        existente = pessoas.save(existente);

        return ResponseEntity.ok(existente);
    }

    @ApiOperation(value = "Remove uma Pessoa pelo CPF")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> remover(@PathVariable String cpf) {
        Pessoa pessoa = pessoas.getOne(cpf);

        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }

        pessoas.delete(pessoa);

        return ResponseEntity.noContent().build();
    }
    
    public static ResponseEntity PessoaValidacao(Pessoa pessoa){
        
        //Validar email se tiver informado
        String email = pessoa.getEmail();
        if (email != null && !PessoasValidation.isEmailValido(email)){
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("E-mail informado é inválido.");
        }
        
        //Validar data de nascimento
        if(!PessoasValidation.isDataNascimentoValida(pessoa.getDataNascimento())){
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Data Nascimento é maior que a data atual.");
        }
        
        //Validar CPF
        if(!PessoasValidation.isCPFValido(pessoa.getCpf())){
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("CPF informado é inválido");
        }
        
        return null;
    }
}
