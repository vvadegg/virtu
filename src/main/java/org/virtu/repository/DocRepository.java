package org.virtu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtu.domain.Doc;

public interface DocRepository extends JpaRepository<Doc, Integer> {
}
