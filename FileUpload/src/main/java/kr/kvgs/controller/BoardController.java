package kr.kvgs.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.kvgs.entity.DetectDis;
import kr.kvgs.entity.Member;
import kr.kvgs.entity.Community;
import kr.kvgs.mapper.BoardMapper;


@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/community")
	public String community(Model model) {
		logger.info("BoardController getCommunity");
		
		List<Community> list_comm = mapper.getCommunity();
		model.addAttribute("list_comm", list_comm);
		
		return "board/05_share";
	}

	@RequestMapping("/history")
	public String history(Model model, HttpSession session) {
		logger.info("BoardController history");
		Member mvo;
		
		mvo = (Member) session.getAttribute("mvo");
		logger.info(" history email : {}, name : {}", mvo.getM_email(), mvo.getM_name());
		
		List<DetectDis> list_detect = mapper.getHistory(mvo);
		model.addAttribute("list_detect", list_detect);
		
		return "board/07_history";
	}

    // 파일전송 요청을 처리하기 위한 컨트롤러
    @RequestMapping("/getFile")
    public String getFile(HttpServletRequest request, Model model,  MultipartFile file){
        String fileName = file.getOriginalFilename(); 
        long fileSize = file.getSize();
        
        String imagePath = request.getServletContext().getRealPath("/");
        String fileFullPath = imagePath + file.getOriginalFilename();
		logger.info("BoardController getFile imagePath : {}, FullPath : {}", imagePath, fileFullPath);
         
		File UploadFolder = new File(imagePath);
        try{
    		if( !UploadFolder.exists() ) {
    			logger.info("BoardController getFile imagePath : {} not exist", imagePath);
    			UploadFolder.mkdir();
    		}
        
			logger.info("BoardController getFile FullPath : {}, FileSize : {}", fileFullPath, fileSize);
	        File destination = new File(fileFullPath);
	        file.transferTo(destination);
        }catch (Exception e){
        	logger.info("에러 : " + e.getMessage());
        }finally {
        	
        }
         
        return "board/03_detect";
    }	


/*
    public Boolean uploadImage(MultipartFile image, String dirName) throws Exception {
        Boolean result = Boolean.FALSE;
        try{
            File folder = new File(dirName);
            if (!folder.exists()) {
            	folder.mkdirs();
            }

            File destination = new File(dirPath + File.separator + image.getOriginalFilename());
            image.transferTo(destination);

            result = Boolean.TRUE;
        }catch (Exception e){
            log.error("에러 : " + e.getMessage());
        }finally {
            return result;
        }
    }



    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        fileService.fileUpload(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }    
*/    
    
}
