package com.example.accessingdatamysql;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReloadRepository extends CrudRepository<Reload,Integer>
{

}
