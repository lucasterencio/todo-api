package com.lucasterencio.todosimple.services;

import com.lucasterencio.todosimple.models.User;
import com.lucasterencio.todosimple.repositories.TaskRepository;
import com.lucasterencio.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    //LISTAR USERS
    public User findById(Long id) {
        //o Optional é que se tiver user, ele mostra, caso contrario, ele nao retorna "null", e sim vazio
        Optional<User> user = this.userRepository.findById(id);
        //se tiver user ele retorna, caso contrario joga no RuntimeException
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    //CRIAR USERS

    @Transactional //muito utilizado para post e put
    public User create(User obj){
        obj.setId(null); //necessario zerar para que o id do novo user nao seja igual a um existente
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    //ATUALIZAR USERS

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    //DELETAR
    public void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possivel excluir, pois há entidades relacionadas");
        }
    }

}
