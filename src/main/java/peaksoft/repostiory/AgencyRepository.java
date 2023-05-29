package peaksoft.repostiory;

import org.springframework.data.annotation.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import peaksoft.entity.Agency;
import peaksoft.entity.House;

import java.util.List;

public interface AgencyRepository  extends JpaRepository<Agency, Long> {
    @Query("select h from Agency a join a.houses h ")
    List<House> getAllAgencyHouse();
@Query("select a from Agency a where a.name ilike :word1 " +
        "or a.email ilike :word1 " +
        "or a.country ilike :word1 " +
        "or a.email ilike :word1")
    List<Agency> searchAgencyByAnyInfo(@Param("word1") String word1);
}
