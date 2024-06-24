package com.lucasterencio.todosimple.repositories;

import com.lucasterencio.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //buscar uma task
    //Optional<Task> findById(Long id);


    //Buscar uma lista de task de um usuario

    List<Task> findByUser_id(Long id);
    //"User_id" quer dizer que vou pegar o id que esta dentro de User
    //Outra forma...

    //@Query(value = "select t from Task t where t.user.id = :id")
    //List<Task> findByUser_Id(@Param("id") Long id);


    //@Query(value = "select * from task t where t.user_id = :id", nativeQuery = true)
    //List<Task> findByUser_Id(@Param("id") Long id);

}
