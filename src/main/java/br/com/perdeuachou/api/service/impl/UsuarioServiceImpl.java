package br.com.perdeuachou.api.service.impl;


import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.repository.UsuarioRepository;
import br.com.perdeuachou.api.service.UsuarioService;
import br.com.perdeuachou.api.utils.PasswordCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Usuario save(Usuario usuario) {
        PasswordCrypt pC = new PasswordCrypt();
        usuario.setSenha(pC.newPassword(usuario.getSenha()));

        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<Long> cadastrosDaSemana() {

        List<Long> cadastrosNaSemana = new ArrayList<>();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Instant instant = c.toInstant();
        LocalDate domingo = instant.atZone(defaultZoneId).toLocalDate();

        Period period = Period.between(domingo, LocalDate.now());

        for(int i = 0; i <= period.getDays(); i++)
            cadastrosNaSemana.add(repository.countAllByDataCadastro(domingo.plusDays(i)));

        return cadastrosNaSemana;
    }

    @Override
    public long cadastros() {
        return repository.count();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long usuario) {
        return repository.findById(usuario);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }
}
