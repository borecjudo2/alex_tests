package com.vertexinc.alextestscript.controller;

import com.vertexinc.alextestscript.service.FakeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
@RestController
@RequestMapping("fake")
@AllArgsConstructor
public class FakeController {

  private final FakeService fakeService;

  @GetMapping("create")
  @ResponseStatus(HttpStatus.OK)
  public void createFakeData(@RequestParam(value = "countFakeRows") int countFakeRows) {
    fakeService.createFakeData(countFakeRows);
  }
}
