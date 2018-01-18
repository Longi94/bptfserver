package in.dragonbra.bptfserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lngtr
 * @since 8/13/2017
 */
@Controller
@RequestMapping("/bptf/legacy")
public class LegacyBptfWebController {

    @RequestMapping(value = "/steamid", method = RequestMethod.GET)
    public String steamId() {
        return "bptf/legacy/steamid";
    }
}
