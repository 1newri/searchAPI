package kr.co.searchAPI.regionproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.searchAPI.regionproject.dto.RegionDTO;

@Repository
public interface RegionRepository  extends JpaRepository<RegionDTO, Long>{

	List<RegionDTO> findAllByRegion(String keyword);

}
