package com.orange.click_2_rent;

public class ClientUtil {
    private  int mImage;
    private  String mService;
    private int mNote;

    public ClientUtil(int mImage, String mService, int mNote) {
        this.mImage = mImage;
        this.mService = mService;
        this.mNote = mNote;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmService() {
        return mService;
    }

    public int getmNote() {
        return mNote;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public void setmService(String mService) {
        this.mService = mService;
    }

    public void setmNote(int mNote) {
        this.mNote = mNote;
    }
}
