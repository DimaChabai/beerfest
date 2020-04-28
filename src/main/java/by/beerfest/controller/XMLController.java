package by.beerfest.controller;

import by.beerfest.entity.dto.Users;
import by.beerfest.entity.impl.User;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.UserRepository;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationUserFindAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

@WebServlet("/info")
@MultipartConfig(fileSizeThreshold = 1000 * 1000
        , maxFileSize = 1000 * 1000)
public class XMLController extends HttpServlet {

    private static final String EMPTY_STRING = "";
    private static final String UNLOAD_DIR = "resources\\data\\out";
    private static final String UPLOAD_DIR = "resources\\data\\in";
    private static final UserRepository userRepository = UserRepository.getInstance();
    private static Logger logger = LogManager.getLogger();
    private static UserRepository repository = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FestSpecification specification = new FestSpecificationUserFindAll();
        String applicationDir = req.getServletContext().getRealPath(EMPTY_STRING);
        String unloadFileDir = applicationDir + UNLOAD_DIR + File.separator;
        File fileSaveDir = new File(unloadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String fileName = unloadFileDir + "users.xml";
        new File(fileName).createNewFile();
        try (OutputStream outputStream = resp.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStream inputStream = new BufferedInputStream(fileInputStream)) {
            List<User> query = repository.query(specification);
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller m = context.createMarshaller();

            Users users = new Users(query);
            m.marshal(users, new FileOutputStream(fileName));

            resp.setContentType("application/xml");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            int readBytes = 0;
            while ((readBytes = inputStream.read()) != -1) {
                outputStream.write(readBytes);
            }
        } catch (RepositoryException | JAXBException e) {
            logger.warn(e);
        }
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JAXBContext jc = JAXBContext.newInstance(Users.class);
            Unmarshaller u = jc.createUnmarshaller();
            Part part = req.getPart("file");
            String path = part.getSubmittedFileName();
            String applicationDir = req.getServletContext().getRealPath(EMPTY_STRING);
            String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;
            part.write(uploadFileDir);
            FileReader reader = new FileReader(uploadFileDir);
            Users users = (Users) u.unmarshal(reader);
            userRepository.add(users.getUsers());
        } catch (JAXBException | RepositoryException e) {
            logger.warn(e);
        }
        resp.sendRedirect("/");
    }
}
