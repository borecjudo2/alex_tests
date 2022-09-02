package com.vertexinc.alextestscript.service;

import java.io.IOException;

/**
 * DESCRIPTION
 *
 * @author Vladislav_Karpeka
 * @version 1.0.0
 */
public interface FakeService {

  void createFakeData(int countFakeRows) throws IOException;

  void deleteFakeData();
}
