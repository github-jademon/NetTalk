package com.example.nettalk.controller;

import com.example.nettalk.dto.token.TokenDto;
import com.example.nettalk.dto.token.TokenRequestDto;
import com.example.nettalk.dto.member.MemberRequestDto;
import com.example.nettalk.dto.member.MemberResponseDto;
import com.example.nettalk.vo.response.DefaultRes;
import com.example.nettalk.vo.response.ResponseMessage;
import com.example.nettalk.vo.response.StatusCode;
import com.example.nettalk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberRequestDto memberRequestDto) {
        try {

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            authService.data.put("data", authService.signup(memberRequestDto));

            if(authService.data.get("data")==null) {
                throw new Exception();
            }

            return new ResponseEntity(DefaultRes
                    .res(authService.res.getStatusCode(), authService.res.getResponseMessage(), authService.data), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(DefaultRes
                    .res(authService.res.getStatusCode(), authService.res.getResponseMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberRequestDto memberRequestDto) {
        try {
            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

            authService.data.put("token", authService.login(memberRequestDto));
//            authService.data.put("message", authService.message);
//            new ResponseEntity<>()
            return new ResponseEntity(DefaultRes
                    .res(StatusCode.OK, authService.res.getResponseMessage(), authService.data), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(DefaultRes
                    .res(authService.res.getStatusCode(), authService.res.getResponseMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

}
