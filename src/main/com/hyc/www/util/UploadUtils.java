
package com.hyc.www.util;

import com.hyc.www.exception.ServiceException;
import com.hyc.www.po.Room;
import com.hyc.www.po.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

import static com.hyc.www.util.UUIDUtils.getUUID;

/**
 * @program
 * @description 用于上传文件
 */
public class UploadUtils {
    /**
     * 用于上传一个文件，返回该文件的文件名
     *
     * @return String 存储的文件名
     * @name
     * @notice none
     */
    public static String upload(Part part) throws IOException {
        String head = part.getHeader("Content-Disposition");
        String filename = getUUID() + head.substring(head.lastIndexOf("."), head.lastIndexOf("\""));
        part.write(filename);
        return filename;
    }


    /**
     * 用于上传照片
     * @param obj 照片的所有者，比如一个user或room
     * @name uploadPhoto
     * @notice none
     */
    public static void uploadPhoto(HttpServletRequest req, Object obj) {
        String photo = null;
        try {
            Part part = req.getPart("photo");
            if (part == null) {
                photo="default.jpg";
            } else if (part.getSize() > 0) {
                photo = upload(part);
            }
        } catch (IOException | ServletException |
                NullPointerException e) {
            e.printStackTrace();
            throw new ServiceException("无法上传照片" + e);
        }

        if (obj instanceof User) {
            User user = (User) obj;
            user.setPhoto(photo);
        }
        if (obj instanceof Room) {
            Room room = (Room) obj;
            room.setPhoto(photo);
        }
    }
}
