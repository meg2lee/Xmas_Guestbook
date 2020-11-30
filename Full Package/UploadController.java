package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AttachDAO;
import com.example.demo.dao.BoardDAO;

@Controller
public class UploadController {
	
	private BoardDAO boardDao;
	
	@Autowired // 사용대상: 1.필드 2.생성자 3.method
	private AttachDAO attachDao;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired /*생성자에 선언*/
	public UploadController(@Qualifier("boardDao") BoardDAO dao) { 
		this.boardDao = dao;
	}
	
	@GetMapping("board") /*사용자리스트보기*/
	public String board_list(Model model) { //정보forward위해 model필요함
		model.addAttribute("boardlist",boardDao.getList());
		/* "boardlist"에 boardDao.getList()객체를 담아 el로 값을 출력하기 위함 */
		return "board_list"; /*bord_list.jsp로 이동*/
	}
	
	@GetMapping("detail") /*개인컨텐츠보기*/
	public String contents(Model model, @RequestParam("num") int num) { /*num을 parameter로 받아 해당 정보를 model로 forward*/
		/*board text 객체와 attach file 객체를 각각 필요로함(가져오는 객체type 다르기 때문)*/
		model.addAttribute("board",boardDao.select(num)); 
		model.addAttribute("fileList",attachDao.fileList(num));
		return "contents"; /*contents.jsp로 이동*/
	}
	
	@RequestMapping("upload") /*upload form 보기*/
	public String getForm() {
		return "upload_form"; 
		/*post방식으로 form을 보내는 것이 아니라 get으로 버튼클릭시 form 가져옴*/
	}
	
	@ResponseBody /*jsp경로가 아닌 문자 그자체로 출력*/
	@RequestMapping("detail") /* 정보수정기능 */
	public String user_update(@ModelAttribute BoardVO b) { /*VO객체로 한번에 보냄*/	
		return boardDao.update(b)+""; /*boolean값을 String으로 문자열화해서 return*/
	}
	
	@ResponseBody
	@DeleteMapping("detail") /* 정보삭제기능 */
	public String board_delete(@RequestParam("num") int num) {
		return boardDao.delete(num)+"";
	}
	
	@GetMapping("download") /*첨부파일 다운로드기능*/
	public String filename(Model model, @RequestParam int num) { /*num을 parameter로 받아 해당 정보를 model로 forward*/		
		model.addAttribute("filelist",attachDao.fileList(num));
		return "contents";
	}
		 	
	@PostMapping("upload")
	@ResponseBody //별도의 jsp없이 바로 브라우저에서 출력
	public boolean upload(@RequestParam("files")MultipartFile[] mfiles, /*파일여러개 배열로 저장*/
			HttpServletRequest request, /* multipart/form-data enctype이 담김*/
			@RequestParam("author") String author
			) {
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/upload");
		/* web-inf는 일반이용자가 접속할 수 없기 때문에 직접접근이 불가능
		   getrealpath로 직접경로를 가져옴
		   c://와 같은 경로는 window에만 존재, war파일로 다른 서버에서 사용하기 위해서는 web-inf경로 사용 要
		   서버로 파일이 넘어오면 savepath에 저장*/
		BoardVO vo = new BoardVO();
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("contents"));
		vo.setAuthor(request.getParameter("author"));
		vo.setWdate(request.getParameter("wdate"));
		int board_key = boardDao.add(vo);
		try {
			for(int i=0;i<mfiles.length;i++) {
				if(!mfiles[i].isEmpty()) {
					mfiles[i].transferTo(
						new File(savePath+"/"+mfiles[i].getOriginalFilename()));
					AttachVO attach = new AttachVO();
					attach.setNum(board_key);
					attach.setFilename(mfiles[i].getOriginalFilename());
					attach.setFilesize(mfiles[i].getSize());
					attach.setContentType(mfiles[i].getContentType());
					boardDao.attach_insert(attach);
				}
			}			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> download( 
	/*파일은 query string으로 넘길 수 없으며, 위의 자료형으로 받아야함*/
			HttpServletRequest request,
			@PathVariable String filename){ /*path변수 filenname 생성*/
		Resource resource = resourceLoader.getResource("WEB-INF/upload/"+filename);
		System.out.println("파일명:"+resource.getFilename());
		String contentType = null;
		try { /*폴더경로를 찾아, 그 곳에 출력*/
			contentType = request.getServletContext()
					.getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(contentType == null) { /*경로 못찾았을경우, 경로설정 위해 다운로드 창 띄워줌*/
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\""+resource.getFilename()+"\"")
				.body(resource);
		}
	}
	
