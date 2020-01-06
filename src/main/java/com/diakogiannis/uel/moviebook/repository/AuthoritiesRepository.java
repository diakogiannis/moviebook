package com.diakogiannis.uel.moviebook.repository;

import com.diakogiannis.uel.moviebook.model.entity.users.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}
