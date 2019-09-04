package kr.co.searchAPI.regionproject.serivce;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.searchAPI.regionproject.dto.RegionDTO;

public interface RegionProjectService {

	boolean readFile(MultipartFile[] files);

	List<RegionDTO> selectRegion(Map<String, Object> params);

	void regionUpdate(RegionDTO regionDTO);
}
