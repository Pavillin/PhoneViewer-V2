package Models;

import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.ArrayList;

public class Phone {
    private String make, model, os;
    private double screensize, memory, frontCameraRes, rearCameraRes, price;
    private Image image;

    public Phone(String make, String model, String os, double screensize) {
        setMake(make);
        setModel(model);
        setOs(os);
        setScreensize(screensize);
    }

    public Phone(String make, String model, String os, double screensize, double memory, double frontCameraRes, double rearCameraRes, double price, Image image) {
        this(make, model, os, screensize);
        setMemory(memory);
        setFrontCameraRes(frontCameraRes);
        setRearCameraRes(rearCameraRes);
        setPrice(price);
        setImage(image);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        try {
            ArrayList<String> validMakes = DBConnect.getPhoneManufacturers();
            if(validMakes.contains(make)){
                this.make = make;
            }else{
                throw new IllegalArgumentException("Invalid make. Enter one of the following makes: "+validMakes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(!model.isEmpty()){
            this.model = model;
        }else{
            throw new IllegalArgumentException("Model cannot be empty.");
        }

    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        if(os == "iOS" || os == "Android" || os == "Blackberry"){
            this.os = os;
        }else{
            throw new IllegalArgumentException("OS must be iOS, Android, or Blackberry.");
        }

    }

    public double getScreensize() {
        return screensize;
    }

    public void setScreensize(double screensize) {
        this.screensize = screensize;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getFrontCameraRes() {
        return frontCameraRes;
    }

    public void setFrontCameraRes(double frontCameraRes) {
        this.frontCameraRes = frontCameraRes;
    }

    public double getRearCameraRes() {
        return rearCameraRes;
    }

    public void setRearCameraRes(double rearCameraRes) {
        this.rearCameraRes = rearCameraRes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
