package shop.ochawork.surfinbird_api.bird;

import javax.validation.constraints.NotBlank;

public class Bird {
    @NotBlank
    private String englishName;

    @NotBlank
    private String scientificName;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }


    public Bird(String englishName, String scientificName) {
        this.englishName = englishName;
        this.scientificName = scientificName;
    }
}
