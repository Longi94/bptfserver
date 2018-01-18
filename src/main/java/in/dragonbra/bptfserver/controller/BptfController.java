package in.dragonbra.bptfserver.controller;

import in.dragonbra.bptfserver.entity.MissingIcon;
import in.dragonbra.bptfserver.openid.SteamOpenID;
import in.dragonbra.bptfserver.repository.MissingIconRepository;
import in.dragonbra.bptfserver.util.HttpUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URI;

/**
 * @author lngtr
 * @since 9/27/2017
 */
@Controller
@RequestMapping("/bptf")
public class BptfController {

    private static final Logger logger = Logger.getLogger(BptfController.class);

    private final SteamOpenID openid = new SteamOpenID();

    @Autowired
    private MissingIconRepository missingIconRepository;

    @Value("${bptf.icon-dir}")
    private String iconDir;

    @RequestMapping("/openid")
    public String openid(HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURL().toString());
        String loginUrl = openid.login(uri.getScheme() + "://" + uri.getAuthority() + "/bptf/auth");

        return "redirect:" + loginUrl;
    }

    @RequestMapping("/auth")
    public String auth(HttpServletRequest request) {
        String user = openid.verify(request.getRequestURL().toString(), request.getParameterMap());
        if (user == null) {
            return "redirect:bptf://cancel";
        }
        return "redirect:bptf://login?id=" + user;
    }

    @RequestMapping(value = "/outpost_search", method = RequestMethod.GET)
    public String outpostSearch() {
        return "bptf/outpost_search";
    }

    @RequestMapping(value = "/icon", method = RequestMethod.GET)
    public void tf2Icon(HttpServletResponse response,
                        @RequestParam(value = "defindex") int defindex,
                        @RequestParam(value = "australium", required = false) Boolean australium,
                        @RequestParam(value = "wear", required = false) Integer wear) {
        String path;

        if (australium != null && australium) {
            path = "australium/" + defindex + ".png";
        } else if (wear != null) {
            path = "decorated/" + defindex + "/" + wear + ".png";
        } else {
            path = defindex + ".png";
        }

        File file = new File(iconDir + path);
        File overrideFile = new File(iconDir + "override/" + path);

        if (overrideFile.exists() && overrideFile.isFile()) {
            HttpUtils.writeToResponse(overrideFile, response);
        } else if (file.exists() && file.isFile()) {
            HttpUtils.writeToResponse(file, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            MissingIcon missingIcon = new MissingIcon(defindex);

            if (australium != null) {
                missingIcon.setAustralium(australium);
            }

            if (wear != null) {
                missingIcon.setWeaponWear(wear);
            }

            try {
                missingIconRepository.save(missingIcon);
            } catch (NonTransientDataAccessException e) {
                if (!(e instanceof DataIntegrityViolationException) ||
                        !(e.getCause() instanceof ConstraintViolationException) ||
                        !((ConstraintViolationException) e.getCause()).getSQLState().equals("23000")) {
                    throw e;
                }
            }
        }
    }
}
