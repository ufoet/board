package upload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class UploadUtil {
	public HashMap<String, String> uploadFile(List<FileItem> items){
		HashMap<String, String> uploadMap=new HashMap<String, String>();
		
		Iterator<FileItem> iter = items.iterator();
		String fieldName = null;
		
		while(iter.hasNext()){
			FileItem item = iter.next();
			if(item.isFormField()){
				fieldName = item.getFieldName();
				String value=null;
				try {
					value = item.getString("utf-8");
				} catch (UnsupportedEncodingException e) {					
					e.printStackTrace();
				}
				uploadMap.put(fieldName, value);
			}else{
				fieldName = item.getFieldName();
				String fileName = item.getName();				
				
				//서버 폴더에 저장
				if(!fileName.isEmpty()&&item.getSize()>0){						
					
					UUID uuid=UUID.randomUUID();					
					
					String path="d:\\upload";
					File uploadFile = new File(path+"\\"+uuid+"_"+fileName);
					uploadMap.put(fieldName, uploadFile.getName());					
					
					try {
						item.write(uploadFile);
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}
			}
		}
		return uploadMap;
	}	

	public HashMap<String, String> getItem(HttpServletRequest req){
		//사용자의 요청이 multipart/form-data 로 왔는지 확인하고
		//일반요소로 온 것과 파일요소로 온 것 분리
		HashMap<String, String> uploadData=null;
		
		//file upload request 여부 확인하기
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(isMultipart){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			ServletFileUpload upload=new ServletFileUpload(factory);
			//업로드 가능 사이즈 지정
			upload.setSizeMax(2000*1024);
			
			//폼에 담긴 내용 중 일반요소와 file로 온 요소 분리
			List<FileItem> items=null;
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {				
				e.printStackTrace();
			}
			uploadData=uploadFile(items);
		}		
		return uploadData;
	}
}



