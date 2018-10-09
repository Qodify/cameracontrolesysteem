package kdg.be.processor.Domain.perception;

public class CameraPercept {


  private Long id;
  private int cameraId;
  private int euroNorm;
  private double latitude;
  private double longitude;
  private int connectedCameraId;
  private double distance;
  private double speedLimit;

  public CameraPercept() {
  }
  @Override
  public String toString() {
    return "CameraPercept{" +
        "cameraId=" + cameraId +
        ", euroNorm='" + euroNorm + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", connectedCameraId=" + connectedCameraId +
        ", distance=" + distance +
        ", speedLimit=" + speedLimit +
        '}';
  }

  public int getConnectedCameraId() {
    return connectedCameraId;
  }

  public void setConnectedCameraId(int connectedCameraId) {
    this.connectedCameraId = connectedCameraId;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public double getSpeedLimit() {
    return speedLimit;
  }

  public void setSpeedLimit(double speedLimit) {
    this.speedLimit = speedLimit;
  }



  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public int getCameraId() {
    return cameraId;
  }


  public void setCameraId(int cameraId) {
    this.cameraId = cameraId;
  }


  public int getEuroNorm() {
    return euroNorm;
  }

  public void setEuroNorm(int euroNorm) {
    this.euroNorm = euroNorm;
  }

}
