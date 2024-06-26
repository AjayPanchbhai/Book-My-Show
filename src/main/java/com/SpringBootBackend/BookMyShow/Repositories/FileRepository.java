package com.SpringBootBackend.BookMyShow.Repositories;

import com.SpringBootBackend.BookMyShow.Models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
