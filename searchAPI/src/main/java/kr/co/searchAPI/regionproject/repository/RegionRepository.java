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

	@Modifying
	@Query(value = " UPDATE tb_region_info "
				+ " SET c_target = :regionDTO.target, "
				+ " c_usage = :regionDTO.usage "
				+ " WHERE c_region_cd = :regionDTO.regionCd"
				, nativeQuery = true
			)
	void saveRegionCd(@Param("regionDTO") RegionDTO regionDTO);

}
