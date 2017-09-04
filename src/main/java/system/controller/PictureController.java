package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.exceptions.NoPictureFoundException;
import system.service.PictureService;

import java.io.IOException;

@Controller
@RequestMapping(path = "/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping(path = "/preview/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    byte[] previewPicture(@PathVariable("appId") String appId){
        byte[] picture = new byte[0];
        try {
            picture = pictureService.getPreviewPicture(Long.valueOf(appId));
        } catch (NoPictureFoundException | IOException e) {
            e.printStackTrace();//TODO what to do with exception
        }
        return picture;
    }

    @GetMapping(path = "/main/{appId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody byte[] mainPicture(@PathVariable("appId") String appId) {
        byte[] picture = new byte[0];
        try {
            picture = pictureService.getMainPicture(Long.valueOf(appId));
        } catch (NoPictureFoundException | IOException e) {
            e.printStackTrace();//TODO what to do with exception
        }
        return picture;
    }
}
