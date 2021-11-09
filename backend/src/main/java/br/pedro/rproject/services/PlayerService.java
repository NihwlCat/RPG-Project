package br.pedro.rproject.services;

import br.pedro.rproject.models.dtos.PlayerDTO;
import br.pedro.rproject.models.entities.Player;
import br.pedro.rproject.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService implements UserDetailsService {

    private final PlayerRepository repository;

    @Autowired
    PlayerService(PlayerRepository repository){
        this.repository = repository;
    }

    public Player recoverPlayer(String id){
        return repository.findById(id).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
    }

    public List<PlayerDTO> findAll(){
        return repository.findAll().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public PlayerDTO findById(String id){
        Player p = recoverPlayer(id);
        return new PlayerDTO(p.getName(), p.getAnnotations());
    }

    public void updateAnnotations(String id, PlayerDTO dto){
        Player p = recoverPlayer(id);
        p.setAnnotations(dto.getAnnotations());
        repository.save(p);
    }

    public PlayerDTO createPlayer(PlayerDTO dto){
        Player p = new Player(dto.getId(), dto.getName(), "DEFAULT", dto.getRole());
        repository.save(p);
        return new PlayerDTO(p);
    }

    public void deletePlayer(String id){
        Player p =  recoverPlayer(id);
        repository.delete(p);
    }

    @Override
    public UserDetails loadUserByUsername(String s){
        final Player player = recoverPlayer(s);
        return User.withUsername(player.getId())
                .password(player.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(player.getRole())))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public void updateAnnotationsFromAuthenticated(PlayerDTO dto) {
        String p = SecurityContextHolder.getContext().getAuthentication().getName();
        Player player = recoverPlayer(p);
        player.setAnnotations(dto.getAnnotations());
        repository.save(player);
    }
}
