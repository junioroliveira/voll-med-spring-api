package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;
    
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        System.out.println(dados);
        pacienteRepository.save(new Paciente(dados));
    }

    public Page<DadosListagemPaciente> listar(@PageableDefault(sort = {"nome"}, size = 10, direction = Direction.ASC) Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

}
