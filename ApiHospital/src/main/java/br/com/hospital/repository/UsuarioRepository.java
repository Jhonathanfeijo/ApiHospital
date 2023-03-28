package br.com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.hospital.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public UserDetails findByLogin(String login);

}
