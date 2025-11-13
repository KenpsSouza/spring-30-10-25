package com.login.exemploService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO saveUser(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.findByEmail(usuarioRequestDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Usuario usuario = new Usuario(usuarioRequestDTO.getName(),
                usuarioRequestDTO.getEmail(),
                usuarioRequestDTO.getPassword());

        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }

    public String login(UsuarioRequestDTO user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return "Usuário não encontrado";
        }
        if (findUser.getPassword().equals(user.getPassword())) {
            return "Login realizado com sucesso";
        } else {
            return "Senha incorreta";
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public String deletar1(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Excluido com sucesso";
        } else {
            return "Esse Id não existe";
        }
    }

    public Usuario atualizar(int id, UsuarioRequestDTO novoUsuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setNome(novoUsuario.getName());
            usuarioAtualizado.setEmail(novoUsuario.getEmail());
            usuarioAtualizado.setPassword(novoUsuario.getPassword());
            usuarioRepository.save(usuarioAtualizado);
            return usuarioAtualizado;
        } else {
            throw new IllegalArgumentException("Esse Id não existe");
        }
    }
}