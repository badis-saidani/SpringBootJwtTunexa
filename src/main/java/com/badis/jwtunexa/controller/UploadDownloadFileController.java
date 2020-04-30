package com.badis.jwtunexa.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.badis.jwtunexa.model.FileModel;
import com.badis.jwtunexa.model.View;
import com.badis.jwtunexa.repository.FileRepository;
import com.badis.jwtunexa.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonView;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UploadDownloadFileController {
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ProductRepository productRepository;
 
    /*
     * MultipartFile Upload
     */
    @PostMapping("/file/upload/{productId}")
    public FileModel uploadMultipartFile(@RequestParam("file") MultipartFile file,
    		@PathVariable String productId) {
    	try {
    		// save file to SQL
	    	FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(),
	    			file.getBytes());
	    	//fileRepository.save(filemode);
	    	//return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
	    	filemode.setProduct(productRepository.findById(Long.parseLong(productId))
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: Product  not find.")));
	    	
	    	LocalDate today = LocalDate.now();
	    	filemode.setChemin(today + "/" + productId + "/" + filemode.getName());
	    	
	    	//check if the product has already a file, if yes, delete it
	    	Optional<FileModel> oldFile = fileRepository.findByProductId(Long.parseLong(productId));
	    	if(oldFile.isPresent()){
	    		System.out.println(oldFile.toString());
	    		filemode.setId(oldFile.get().getId());
	    	}
	    		
	    	
	    	return fileRepository.save(filemode);
		} catch (	Exception e) {
			//return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
			return null;
		}    
    }
    
    /*
	 * List All Files
	 */
    @JsonView(View.FileInfo.class)
	@GetMapping("/api/file/all")
	public List<FileModel> getListFiles() {
		return fileRepository.findAll();
	}
	
       
    /*
     * Download Files
     */
       @JsonView(View.FileInfo.class)
	@GetMapping("/api/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Optional<FileModel> fileOptional = fileRepository.findById(id);
		
		if(fileOptional.isPresent()) {
			FileModel file = fileOptional.get();
			//System.out.println("look dis: " + file.toString());
			ResponseEntity<byte[]> result;
			result = ResponseEntity.ok()
					//.header(HttpHeaders.AUTHORIZATION, "'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYWRpcyIsImlhdCI6MTU1NTMzNzM1NSwiZXhwIjoxNTU1NDIzNzU1fQ.R2Y58PfNdzb65GocvrAAZp-1igb41Ie_rXsCgHOchSU8yzz-0zPRqZiJ_n0yt8JMQER-1krpGLi7TtUFZHpnVw")
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
					//.header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJpYXQiOjE1NTUzMzg4MTksImV4cCI6MTU1NTQyNTIxOX0.MQ5SJ4jJb27PaeNX7mpARPxr3Naqfy4UO8SfGfzmsrgBu20HDX-r8scc-frnZqqk7YyHSkuEsIBkECPemRTbIA")
					.body(file.getPic());	
			
			System.out.println(result.toString());
			
			return result;
			
		}
		return ResponseEntity.status(404).body(null);
	}
	
	/*
	 * List All Files
	 */
	@DeleteMapping("/file/delete/{id}")
	public boolean DeleteFileById(@PathVariable Long id) {
		 fileRepository.deleteById(id);
		 return true;
	}
	
}
