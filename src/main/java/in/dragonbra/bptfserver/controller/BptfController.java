package in.dragonbra.bptfserver.controller;

import in.dragonbra.bptfserver.openid.SteamOpenID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

/**
 * @author lngtr
 * @since 9/27/2017
 */
@Controller
@RequestMapping("/bptf")
public class BptfController {

    private static final Logger logger = LoggerFactory.getLogger(BptfController.class);

    private final SteamOpenID openid = new SteamOpenID();

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

}
