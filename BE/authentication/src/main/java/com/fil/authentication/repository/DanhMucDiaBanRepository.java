package com.fil.authentication.repository;

import com.fil.authentication.models.DanhMucDiaBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhMucDiaBanRepository extends JpaRepository<DanhMucDiaBan, Long> {

    DanhMucDiaBan findByCode(String code);

    List<DanhMucDiaBan> findAllByParentCode(String parentCode);
}
