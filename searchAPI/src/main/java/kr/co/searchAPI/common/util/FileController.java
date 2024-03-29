package kr.co.searchAPI.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.searchAPI.ecosystem.service.EcoSystemProjectService;
import kr.co.searchAPI.housing.service.HousiongProjectService;
import kr.co.searchAPI.regionproject.serivce.RegionProjectService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	RegionProjectService regionProjectService;
	
	@Autowired
	EcoSystemProjectService ecoSystemProjectService;
	
	@Autowired
	HousiongProjectService housiongProjectService;

	@PostMapping("/upload")
	public String upload(
			@RequestParam String msg,
			@RequestParam MultipartFile[] files
			) throws Exception{
		log.info("readFile msg : {} ", msg);
		log.info("readFile MultipartFile.length : {} ", files.length);
		
		String result = "";
		
		boolean isResult = false;
		
		if("Region".equals(msg)) {
			isResult = regionProjectService.readFile(files);
			if(isResult) {
				result = "Success";
			}else {
				result = "Fail";
			}
		}else if("Ecosystem".equals(msg)) {
			isResult = ecoSystemProjectService.readFile(files);
			
		}else if("Housing".equals(msg)) {
			isResult = housiongProjectService.readFile(files);
		}else {
			log.info("");
		}
		
		
		return result;
	}
	
}
