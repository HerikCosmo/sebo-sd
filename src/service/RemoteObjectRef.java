package service;

import java.io.Serializable;

public class RemoteObjectRef implements Serializable {
  private String objectName;

  public RemoteObjectRef(String objectName) {
    this.objectName = objectName;
  }

  public String getObjectName() {
    return objectName;
  }
}
