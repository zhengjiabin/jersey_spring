package bean;

import java.io.File;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

public class TestUpload {
    private Client client;
    
    @Before
    public void before() {
        ClientConfig config = new DefaultClientConfig();
        this.client = Client.create(config);
    }
    
    @Test
    public void upload(String[] args) {
        String url = "http://localhost:8080/jersey_spring/service/uploadFile2";
        WebResource resource = client.resource(url);
        
        String filePath = "F:/temp/ceshi.png";
        File file = new File(filePath);
        FileDataBodyPart bodyPart = new FileDataBodyPart("file", file);
        
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        FormDataMultiPart photoFile = formDataMultiPart.field("fileName", "baozi");
        photoFile.bodyPart(bodyPart);
        
        Builder builder = resource.type(MediaType.MULTIPART_FORM_DATA);
        String fileFullName = builder.post(String.class, formDataMultiPart);
        
        System.out.println(fileFullName);
    }
}
