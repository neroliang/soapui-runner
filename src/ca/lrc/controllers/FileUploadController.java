package ca.lrc.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ca.lrc.beans.Upload;

@Controller
public class FileUploadController {
	@RequestMapping(value="upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(Model model, @ModelAttribute Upload upload){
        if (!upload.getFile().isEmpty()) {
            try {
                byte[] bytes = upload.getFile().getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(upload.getName())));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + upload.getName() + "!";
            } catch (Exception e) {
                return "You failed to upload " + upload.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + upload.getName() + " because the file was empty.";
        }
    }
}
