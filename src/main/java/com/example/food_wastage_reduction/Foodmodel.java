package com.example.food_wastage_reduction;

public class Foodmodel {
  String Food;
  String Quantity;
  String UserAddress;
  String UserPincode;
  String UserState;
  String Usercity;

  public Foodmodel() {
  }

  public Foodmodel(String food, String quantity, String userAddress, String userPincode, String userState, String usercity) {
    Food = food;
    Quantity = quantity;
    UserAddress = userAddress;
    UserPincode = userPincode;
    UserState = userState;
    Usercity = usercity;
  }

  public String getFood() {
    return Food;
  }

  public void setFood(String food) {
    Food = food;
  }

  public String getQuantity() {
    return Quantity;
  }

  public void setQuantity(String quantity) {
    Quantity = quantity;
  }

  public String getUserAddress() {
    return UserAddress;
  }

  public void setUserAddress(String userAddress) {
    UserAddress = userAddress;
  }

  public String getUserPincode() {
    return UserPincode;
  }

  public void setUserPincode(String userPincode) {
    UserPincode = userPincode;
  }

  public String getUserState() {
    return UserState;
  }

  public void setUserState(String userState) {
    UserState = userState;
  }

  public String getUsercity() {
    return Usercity;
  }

  public void setUsercity(String usercity) {
    Usercity = usercity;
  }
}
