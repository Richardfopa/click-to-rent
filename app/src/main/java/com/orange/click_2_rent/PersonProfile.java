package com.orange.click_2_rent;

public class PersonProfile {
    private int icone;
    private String description;

    public PersonProfile(int icone, String description) {
        this.icone = icone;
        this.description = description;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
