package com.wind.web;import com.wind.common.Constant;import com.wind.common.PaginatedResult;import com.wind.config.WebMvcConfig;import com.wind.exception.ResourceNotFoundException;import com.wind.mybatis.pojo.User;import com.wind.service.UserService;import lombok.Data;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.stereotype.Controller;import org.springframework.ui.ModelMap;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.support.ServletUriComponentsBuilder;import javax.servlet.http.HttpSession;import java.net.URI;import java.util.Optional;@Controller@RequestMapping("/user")public class UserController {    @Autowired    private UserService userService;    //登录    @RequestMapping("/login")    public String login(@ModelAttribute User user, HttpSession session){        Optional<User> myUser = userService.getEntityByEntity(user);        if(myUser.isPresent()){            user =  myUser.get();            session.setAttribute(WebMvcConfig.SESSION_KEY, user);        }        String url= "user/"+user.getId();        return "redirect:/"+url;    }    //id查找信息    @GetMapping("/{id}")    public String getUserById(@PathVariable int id,ModelMap modelMap) {        modelMap.put("userInfo", userService.getEntityByID(id));        return "admin/index";    }    //用户列表    @GetMapping("/index/{page}")    public String search(@PathVariable int page,ModelMap modelMap) {        PaginatedResult PageInfo = new PaginatedResult().setData(userService.getAll(page))                    .setCurrentPage(page)                    .setCount(userService.getCount());        modelMap.put("PageInfo", PageInfo);        return "admin/user/index";    }    //跳转添加界面    @RequestMapping(value = "/add", method = RequestMethod.GET)    public String add(ModelMap map) {        User user = new User();        map.addAttribute("user",user);        return "admin/user/form";    }    //跳转修改界面    @RequestMapping(value = "/edit/{id}")    public String add(@PathVariable int id,ModelMap map) {        assertUserExist(id);        User user = new User();        Optional<User> myUser = userService.getEntityByID(id);        if(myUser.isPresent()){            user =  myUser.get();        }        map.addAttribute("user",user);        return "admin/user/form";    }    //增加,修改用户    @PostMapping("/change")    public ResponseEntity<?> postUser(@ModelAttribute User user) {        System.out.println(user.toString());       if(user.getId()==null){           userService.addEntity(user);           URI location = ServletUriComponentsBuilder               .fromCurrentRequest()               .path("/{id}")               .buildAndExpand(user.getId())               .toUri();           return ResponseEntity               .created(location)               .body(user);       }else{           System.out.println("");           assertUserExist(user.getId());           userService.modifyEntityById(user);           return ResponseEntity               .status(HttpStatus.ACCEPTED)               .body(user);       }    }//    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)//    @ResponseBody//    public String edit(User user, ModelMap map) {////    }//    @GetMapping("/all/{page}")//    @ResponseBody//    public ResponseEntity<?> search(//        @RequestParam(value = "type", required = false, defaultValue = "") String type,//        @RequestParam(value = "value", required = false, defaultValue = "") String value,//        @PathVariable int page) {//        if ("".equals(type)) {//            return ResponseEntity//                .ok(new PaginatedResult()//                    .setData(userService.getAll(page))//                    .setCurrentPage(page)//                    .setCount(userService.getCount()));//        } else {//            assert ("account".equals(type));//            return ResponseEntity//                .ok(new PaginatedResult()//                    .setData(userService.getAll(type, value, page))//                    .setCurrentPage(page)//                    .setCount(userService.getCount(type, value)));//        }//    }    @PutMapping    @ResponseBody    public ResponseEntity<?> putUser(@RequestBody User user) {        assertUserExist(user.getId());        userService.modifyEntityById(user);        return ResponseEntity            .status(HttpStatus.ACCEPTED)            .body(user);    }    ////    @PutMapping("/password")//    @ResponseBody//    public ResponseEntity<?> changePassword(@RequestBody changePasswordForm form) {//        OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();//        Optional<User> user = userService.getUserByName(((SecurityUser) auth.getPrincipal()).getUsername());//        if (user.isPresent() && user.get().getPassword().equals(form.oldPassword)) {//            User instance = user.get();//            instance.setPassword(form.newPassword);//            userService.modifyUserById(instance);//            return ResponseEntity//                    .status(HttpStatus.OK)//                    .build();//        } else {//            return ResponseEntity//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)//                    .build();//        }//    }    //删除    @RequestMapping("/delete/{id}")    public ResponseEntity<?> deleteUser(@PathVariable int id,HttpSession session) {        assertUserExist(id);        boolean result = userService.deleteEntityById(id);        if (result)            return ResponseEntity                .accepted()                .build();        else            return ResponseEntity                .notFound()                .build();    }    private void assertUserExist(int id) {        userService            .getEntityByID(id)            .orElseThrow(() -> new ResourceNotFoundException()                .setResourceName(Constant.RESOURCE_USER)                .setId(id));    }    @Data    static class changePasswordForm {        private String oldPassword;        private String newPassword;    }}