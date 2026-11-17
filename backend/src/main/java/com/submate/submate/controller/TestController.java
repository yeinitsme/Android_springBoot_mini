package com.submate.submate.controller;

import com.submate.submate.entity.User;
import com.submate.submate.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

//이 클래스가 http 요청을 받아서 응답하는 역할을 한다고 spring에 알려주는 코드
@RestController
public class TestController {

    private final UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //누군가 GET 방식으로 /hello 주소에 요청하면 아래 메서드를 실행하라고 알려줘
    @GetMapping("/hello")

    public String hello() {
        return "Hello! My Server is Working!";
    }

    /*
    Map<String, String>에서 꺾쇠 안의 두 String은 우리가 어떤 종류의 데이터를 담을지 지정한 거야.
    - 첫 번째 String: 데이터를 찾을 때 사용하는 키(key)
    - 두 번째 String: 실제로 저장하는 값(value)

    즉, Map<String, String>은 문자열을 키로 사용하고, 문자열을 값으로 저장하는 Map이라는 뜻.
    data라는 Map. data가 이름. map이 자료형
     */
    //POST 요청을 받을 주소를 정하기
    @PostMapping("/api/test")
    public Map<String, String> receiveData(

            // @RequestBody -> 요청에 담겨 온 JSON 데이터 읽기
            // Map<String, String> data: 전달받은 데이터를 저장
            @RequestBody Map<String, String> data) {

        String name = data.get("name");
        String major = data.get("major");

        //받은 데이터를 바탕으로 응답 돌려줌
        return Map.of(
                "message", "데이터를 잘 받았어요!",
                "receivedName", name,
                "receivedMajor", major
        );
    }
    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User newUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setName(newUser.getName());
        user.setMajor(newUser.getMajor());

        return userRepository.save(user);
    }
    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "회원 삭제 완료!";
    }
}


