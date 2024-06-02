package com.sonarplatform.programmingtest1rssfeed.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/yeahbutstill")
public class DateController {
    // ini untuk formater response
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

    @GetMapping("/date")
    // ini untuk otomatis return value dari Type si methodnya menjadi isi body yang dituliskan ke HttpServletResponse
    // cukup tambahkan annotation
    @ResponseBody
    // disini karena kita sudah membuat coverternya di package util yang bernama classnya StringToDateConverter
    // nanti secara otomatis Spring akan mencari converter yang bisa melakukan coversi dari Tipe data String dari RequestParam ke Date
    // jadi nanti kalau dikirim, format requestnya harus kaya yang ada di Class StringToDateConverter
    // perlu diingat juga tipe data yang dasar seperti String, Integer, Boolean, Long itu semuanya sudah di conversi otomatis
    // menggunakan Spring Converter
    public String getDate(@RequestParam(name = "date") Date date) {
        // balikan tanggal yang diterima tapi formatnya menggunakan format yang berbeda dari si dateFormat ini
        return "Date: " + dateFormat.format(date);
    }
}
