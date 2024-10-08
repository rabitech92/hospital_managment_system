package com.file.fileuploaddownload.repository;

import com.file.fileuploaddownload.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo,String> {
}
