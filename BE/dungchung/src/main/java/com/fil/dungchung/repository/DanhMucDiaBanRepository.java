package com.fil.dungchung.repository;

import com.fil.dungchung.models.DanhMucDiaBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucDiaBanRepository extends JpaRepository<DanhMucDiaBan, Long> {
}
