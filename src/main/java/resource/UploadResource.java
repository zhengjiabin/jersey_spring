package resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.UploadFileService;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.spi.resource.Singleton;

/**
 * 文件上传
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@Singleton
@Path(value = "/upload")
public class UploadResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo uriInfo;
    
    @SuppressWarnings("unused")
    @Context
    private Request request;
    
    @SuppressWarnings("unused")
    @Context
    private HttpHeaders httpHeader;
    
    @Autowired
    private UploadFileService uploadFileService;
    
    @POST
    @Path(value = "uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String uploadFile(FormDataMultiPart form) {
        String fileFullName = uploadFileService.uploadFile(form);
        return fileFullName;
    }
    
    @POST
    @Path(value = "uploadFile2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String uploadFile2(@FormDataParam("file")
    InputStream fileInputStream, @FormDataParam("file")
    FormDataContentDisposition fileContentDisposition) {
        String newFileName = "haha";
        String fileFullName = uploadFileService.uploadFile2(newFileName, fileInputStream, fileContentDisposition);
        return fileFullName;
    }
}
