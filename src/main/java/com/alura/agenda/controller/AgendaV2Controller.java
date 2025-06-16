package com.alura.agenda.controller;

import com.alura.agenda.exception.BusinessException;
import com.alura.agenda.exception.NotFoundException;
import com.alura.agenda.model.Contato;
import com.alura.agenda.repository.AgendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/v2/agendaTelefonica")
public class AgendaV2Controller {

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/{id}")
    public Contato consultarContato(@PathVariable Long id) {
        // return agendaRepository.findById(id).orElseThrow(() -> new NotFoundException("Contato não encontrado"));
        // return agendaRepository.findById(id).orElseThrow(NotFoundException::new);

        Optional<Contato> contato = agendaRepository.findById(id);

        if (contato.isEmpty()) {
            throw new NotFoundException("Contato não encontrado");
        }

        return contato.get();
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

        if (agendaRepository.findByEmail(contato.getEmail()).isPresent()) {
            throw new BusinessException("Email já cadastrado");
        }

        Contato contatoSalvo = agendaRepository.save(contato);

        return contatoSalvo;
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@RequestBody @Valid Contato contato, @PathVariable Long id) {
        contato.setId(id);

        Contato contatoAtualizado = agendaRepository.save(contato);

        return contatoAtualizado;
    }

    @PatchMapping("/{id}")
    public Contato atualizarParcialmenteContato(@PathVariable Long id,
                                                @RequestParam("email") String email) {
        Contato contato = agendaRepository.findById(id).get();

        contato.setEmail(email);

        Contato contatoAtualizado = agendaRepository.save(contato);

        return contatoAtualizado;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Contato deletarContato(@PathVariable Long id) {
        // Abordagem completa

        Contato contato = agendaRepository.findById(id).get();

        agendaRepository.delete(contato);

        return contato;

        // Abordagem simple

        // agendaRepository.deleteById(id);
    }

    @DeleteMapping("/{id}/teste")
    public ResponseEntity<Contato> deletarContatoTeste(@PathVariable Long id) {
        Optional<Contato> contato = agendaRepository.findById(id);

        if (contato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        agendaRepository.delete(contato.get());

        return ResponseEntity.ok(contato.get());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleNotFoundExceptions(NotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

}
