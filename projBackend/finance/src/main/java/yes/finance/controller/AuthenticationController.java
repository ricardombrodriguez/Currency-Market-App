package yes.finance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yes.finance.model.User;
import yes.finance.services.UserService;

@CrossOrigin
@RestController
public class AuthenticationController {

  @Autowired
  private UserService userService;

  @GetMapping("/login")
  public Map<String, Object> login(@RequestParam String email, @RequestParam String password) {
    Map<String, Object> response = new HashMap<>();

    User user = userService.getUserLogin(email, password);

    if (user == null) response.put("success", false);
    else {
      response.put("success", true);
      response.put("id", user.getId());
    }

    return response;
  }

  @PostMapping("/signup")
  public Map<String, Object> signup(@RequestBody User user) {
    Map<String, Object> response = new HashMap<>();

    if (userService.isEmailInUse(user.getEmail())) response.put("success", false);
    else {
      userService.saveUser(user);
      response.put("success", true);
      response.put("id", user.getId());
    }

    return response;
  }

}
