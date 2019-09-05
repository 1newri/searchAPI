package kr.co.searchAPI.ecosystem.service;

import org.springframework.web.multipart.MultipartFile;

public interface EcoSystemProjectService {

	boolean readFile(MultipartFile[] files);

}
