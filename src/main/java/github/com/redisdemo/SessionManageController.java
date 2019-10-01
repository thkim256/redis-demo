package github.com.redisdemo;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionManageController {
  @GetMapping("/generate")
  public String login(HttpSession httpSession, @RequestParam(required = false) Integer dummySize) {
    if (!httpSession.isNew()) {
      httpSession.invalidate();
      return "session is created - " + httpSession.getId();
    }
    if (dummySize != null && dummySize > 0) {
      httpSession.setAttribute("dummy", new byte[dummySize]);
    }
    return "Generate session - " + httpSession.getId();
  }

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

//  @Autowired private RedisOperationsSessionRepository sessionRepository;
//
//  @GetMapping("/get-direct")
//  public String get(@RequestParam String id) {
//    final Session session = sessionRepository.findById(id);
//    return String.valueOf(session.getCreationTime());
//  }

  @GetMapping("get")
  public String getByHeader() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }
}
