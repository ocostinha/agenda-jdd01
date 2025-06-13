package com.alura.agenda.controller;

import com.alura.agenda.model.Contato;
import com.alura.agenda.repository.AgendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agendaTelefonica")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/{id}")
    public Contato consultarContato(@PathVariable Long id) {
        Contato contato = agendaRepository.findById(id).get();

        return contato;
    }

    @GetMapping
    public List<Contato> consultarTodosOsContatos() {
        List<Contato> contatos = agendaRepository.findAll();

        return contatos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato cadastrarContato(@RequestBody @Valid Contato contato) {
        contato.setId(null);

        Contato contatoSalvo = agendaRepository.save(contato);

        return contatoSalvo;
    }

    @PutMapping("/{id}")
    public String atualizarContato() {
        return "Atualizar Contato";
    }

    @PatchMapping("/{id}")
    public String atualizarParcialmenteContato() {
        return "Atualizar parcialmente Contato";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deletarContato() {
        return "Deletar Contato";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
