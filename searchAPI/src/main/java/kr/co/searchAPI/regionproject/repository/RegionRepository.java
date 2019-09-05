package kr.co.searchAPI.regionproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.searchAPI.regionproject.dto.RegionCdDTO;
import kr.co.searchAPI.regionproject.dto.RegionDTO;

@Repository
public interface RegionRepository  extends JpaRepository<RegionDTO, Long>{

	List<RegionDTO> findByRegionCdDto(RegionCdDTO cdDto);

	RegionDTO findByRegionCd(String regionCd);
	
}
